package com.mahakim.app.services.dossiers;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.request.model.DossierRequestBody;

public interface DossierService {

	
	DossierRequestBody getDossierById();

	List<DossierEntity> actualiserDossiers(String year, String codeDossier, String StartWithNumeroDossier, int Range,
			int idJuidiction) throws ParseException, StreamReadException, DatabindException, IOException;

//	List<DossierEntity> addOrUpdateDecisionsToMultipleDossiers(
//			Map<DossierEntity, List<DecisionEntity>> dossierDecisionsMap);
}
