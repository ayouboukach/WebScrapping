package com.mahakim.app.services.dossiers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.mapper.DossierMapper;
import com.mahakim.app.repos.DossierRepo;
import com.mahakim.app.request.model.DossierRequestBody;
import com.mahakim.app.services.dossiers.decisions.DecisionService;
import com.mahakim.app.services.dossiers.parties.PartieService;
import com.mahakim.app.services.juridictions.JuridictionService;
import com.mahakim.app.utils.DossierUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DossierServiceImpl implements DossierService {

    private final DossierRepo dossierRepo;
    private final DossierMapper dossierMapper;
    private final DecisionService decisionService;
    private final JuridictionService juridictionService;
    private final APIClientService apiClientService;
    private final PartieService partieService;

    @Override
    public List<DossierEntity> actualiserDossiers(String year, String codeDossier, String startWithNumeroDossier, int range, 
    		int idJuidiction) throws ParseException, StreamReadException, DatabindException, IOException {
		List<DossierEntity> dossiersApi = null;
		try {
			dossiersApi = chercherDossiersDepuisApi(year, codeDossier, startWithNumeroDossier, idJuidiction, range);
			if (dossiersApi.isEmpty())
				return dossiersApi;
			actualiserDossiersAvecLeursInfo(dossiersApi);
			dossierRepo.saveAll(dossiersApi);
			partieService.actualiserPartiesPourDossiersEtEnregister(dossiersApi);
			log.info("Dossiers actualisés avec succés");
		} catch (Exception e) {
			log.info("Erreur lors l'actualisation les dossiers!, {}", e.getMessage());
		}
		return dossiersApi;
    }

    @Override
    public DossierRequestBody getDossierById() {
        return null;
    }
    
    private List<DossierEntity> chercherDossiersDepuisApi(String year, String codeDossier, String startWithNumeroDossier, int idJuidiction, int range) throws ParseException {
        return dossierMapper.listBodyToEntity(
            IntStream.range(0, range)
                .mapToObj(i -> apiClientService.getDossierByNumeroComplet(
                		DossierUtils.genererNumeroDossier(year, codeDossier, startWithNumeroDossier, i),
                        Integer.toString(idJuidiction)))
                .filter(Objects::nonNull)
                .collect(Collectors.toList())
        );
    }

    private void actualiserDossiersAvecLeursInfo(List<DossierEntity> dossiers) throws ParseException {
        dossiers = juridictionService.actualiserJuridictionsDbPourDossiers(dossiers);
        dossiers = decisionService.ajouterDecisionsApiAuxDossiers(dossiers);
        log.info("Dossiers mis à jour avec juridictions et décisions");
    }
}
