package com.mahakim.app.services.dossiers.decisions;

import java.text.ParseException;
import java.util.List;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;

public interface DecisionService {

	
    void createDecisions();
    
    DecisionEntity getDecisionById(int id);
    
    List<DecisionEntity> getDecisionsByDossier();
    
	List<DecisionEntity> saveDecisionsByIdDossierFromAPI(int id) throws ParseException;

	List<DecisionEntity> refreshDecisionsByDossierFromAPI(DossierEntity dossier) throws ParseException;
}
