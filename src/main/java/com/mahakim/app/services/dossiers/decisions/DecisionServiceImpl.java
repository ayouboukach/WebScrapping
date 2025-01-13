package com.mahakim.app.services.dossiers.decisions;

import java.text.ParseException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.helpers.DecisionHelper;
import com.mahakim.app.helpers.DossierHelper;
import com.mahakim.app.mapper.DecisionMapper;
import com.mahakim.app.repos.DecisionRepo;
import com.mahakim.app.repos.DossierRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DecisionServiceImpl implements DecisionService {

    private final DecisionRepo decisionRepo;
    private final DossierRepo dossierRepo;
    private final DecisionMapper decisionMapper;
    private final APIClientService apiClientService;

    @Override
    public List<DossierEntity> ajouterDecisionsApiAuxDossiers(List<DossierEntity> dossiers) throws ParseException {
        Map<DossierEntity, List<DecisionEntity>> mapDecisionsDoitActualiserParDossier = grouperDecisionsPourDossierVersMap(dossiers);
        mapDecisionsDoitActualiserParDossier = actualiserDecisionsApiParListDossiers(mapDecisionsDoitActualiserParDossier);
        return DossierHelper.convertirMapVersDossiers(mapDecisionsDoitActualiserParDossier);
    }

    @Override
    public List<DecisionEntity> sauvegarderDecisionsApiIdDossierDansDb(int id) throws ParseException {
        return decisionRepo.saveAll(rechercherDecisionsApiPourIdDossier(id));
    }

    private Map<DossierEntity, List<DecisionEntity>> grouperDecisionsPourDossierVersMap(List<DossierEntity> dossiers) {
        List<DecisionEntity> decisions = rechercherDecisionsDbPourDossiers(dossiers);
        return DossierHelper.convertirDossiersEtDecisionsVersMap(decisions, dossiers);
    }

    private List<DecisionEntity> rechercherDecisionsDbPourDossiers(List<DossierEntity> dossiers) {
        return Optional.ofNullable(decisionRepo.findAllByDossierIn(dossiers))
                .orElse(Collections.emptyList());
    }

    private List<DecisionEntity> rechercherDecisionsApiPourIdDossier(int dossierId) throws ParseException {
        return decisionMapper.listBodyToEntity(
                apiClientService.rechercherDecisionsPourIdDossier(String.valueOf(dossierId), "DC"));
    }

    private Map<DossierEntity, List<DecisionEntity>> actualiserDecisionsApiParListDossiers(Map<DossierEntity, List<DecisionEntity>> decisionsGroupees) {
        return decisionsGroupees.entrySet().stream()
                .map(this::actualiserDecisionsParDossier)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private Map.Entry<DossierEntity, List<DecisionEntity>> actualiserDecisionsParDossier(Map.Entry<DossierEntity, List<DecisionEntity>> entree) {
        DossierEntity dossier = entree.getKey();
        List<DecisionEntity> decisions = entree.getValue();
        try {
            List<DecisionEntity> decisionsActualisees = actualiserDecisionsApiPourDossier(decisions, dossier);
            DossierHelper.mergeEntreDossierEtLeurDecisions(dossier, decisionsActualisees);
            return new AbstractMap.SimpleEntry<>(dossier, decisionsActualisees);
        } catch (ParseException e) {
            log.error("Error refreshing decisions for dossier: " + dossier.getIdDossierCivil(), e);
            return new AbstractMap.SimpleEntry<>(dossier, Collections.emptyList());
        }
    }

    private List<DecisionEntity> actualiserDecisionsApiPourDossier(List<DecisionEntity> decisionsDb, DossierEntity dossier) throws ParseException {
    	List<DecisionEntity> decisionsApi = rechercherDecisionsApiPourIdDossier(dossier.getIdDossierCivil());
        if (!decisionsDb.isEmpty()) {
            DecisionHelper.mettreAJourClesPremiere(decisionsApi, decisionsDb);
        }
        return decisionsApi;
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void actualiserDecisionsDepuisApiChasque5Secondes() {
        try {
            log.info("Démarrage... d'actualiser les decisions sont les audiances suivantes passées!");
            var decisionsDbSontAudianceSuivantPassee = decisionRepo.findDecisionsWithPastDateAndExcludedType();
            log.info("Le nombre des décisions trouvées {}", decisionsDbSontAudianceSuivantPassee.size());
            if (decisionsDbSontAudianceSuivantPassee.isEmpty()) {
                log.warn("Aucune décision trouvée pour mise à jour.");
                return;
            }
            log.info("Recherche... dans db les dossiers liés à ces décisions");
            var dossiers = dossierRepo.findDossiersByDecisionsIn(decisionsDbSontAudianceSuivantPassee);
            if (dossiers.isEmpty()) {
                log.warn("Aucun dossier trouvé pour ces décisions !");
                return;
            }
            log.info("Le nombre des dossiers trouvés {}", dossiers.size());
            var decisionsActualisees = actualiserDecisionsApiParListDossiers(DossierHelper.convertirDossiersVersMap(dossiers));
            dossierRepo.saveAll(DossierHelper.convertirMapVersDossiers(decisionsActualisees));
            log.info("Toutes les décisions sont les audiances suivantes passées sont actualisées!");
        } catch (Exception e) {
            log.error("Erreur lors de l'actualisation des décisions !, {}", e.getMessage(), e);
        }
    }

	@Override
	public void creerDecisions() {
	}
}
