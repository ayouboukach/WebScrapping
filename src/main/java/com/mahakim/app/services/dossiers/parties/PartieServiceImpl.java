package com.mahakim.app.services.dossiers.parties;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.PartieEntity;
import com.mahakim.app.mapper.PartieMapper;
import com.mahakim.app.repos.PartieRepo;
import com.mahakim.app.services.avocats.AvocatService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PartieServiceImpl implements PartieService {

    private final APIClientService apiClientService;
    private final PartieMapper partieMapper;
    private final PartieRepo partieRepo;
    private final AvocatService avocatService;

    @Override
    public void createParties() {
    }

    @Override
    public PartieEntity getPartieById(int id) {
        return null;
    }

    @Override
    public List<PartieEntity> getPartiesByDossier() {
        return null;
    }

    @Override
    public List<PartieEntity> actualiserPartiesPourDossiersEtEnregister(List<DossierEntity> dossiers) throws ParseException {
        List<PartieEntity> partiesDoitEnregistees = dossiers.stream()
                .peek(dossier -> dossier.setParties(ajouterDossierVersParties(chercherPartiesPourDossier(dossier), dossier)))
                .flatMap(dossier -> dossier.getParties().stream())
                .collect(Collectors.toList());

        partiesDoitEnregistees = avocatService.actualiserAvocatsPourParties(partiesDoitEnregistees);
        return partieRepo.saveAll(partiesDoitEnregistees);
    }

    private List<PartieEntity> ajouterDossierVersParties(List<PartieEntity> parties, DossierEntity dossier) {
        return parties.stream()
                .peek(partie -> partie.setDossier(dossier))
                .collect(Collectors.toList());
    }

    private List<PartieEntity> chercherPartiesPourDossier(DossierEntity dossier) {
        return partieMapper.listBodyToEntity(
                apiClientService.getPartiesByIdDossier(String.valueOf(dossier.getIdDossierCivil()), "DC"));
    }
}
