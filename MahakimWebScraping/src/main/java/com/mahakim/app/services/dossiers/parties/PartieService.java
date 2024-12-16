package com.mahakim.app.services.dossiers.parties;

import java.text.ParseException;
import java.util.List;
import java.util.Set;

import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.PartieEntity;

public interface PartieService {

	void createParties();

	PartieEntity getPartieById(int id);

	List<PartieEntity> getPartiesByIdDossierFromAPI(int id) throws ParseException;

	List<PartieEntity> getPartiesByDossier();

//	Set<PartieEntity> refreshPartiesByIdDossierFromAPI(int id) throws ParseException;

	Set<PartieEntity> refreshPartiesByDossierFromAPI(DossierEntity dossier) throws ParseException;

}
