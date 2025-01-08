package com.mahakim.app.services.dossiers.decisions;

import java.text.ParseException;
import java.util.List;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;

public interface DecisionService {

	
    void creerDecisions();
    
	List<DecisionEntity> sauvegarderDecisionsApiIdDossierDansDb(int id) throws ParseException;

	List<DossierEntity> ajouterDecisionsApiAuxDossiers(List<DossierEntity> dossiers) throws ParseException;

	void actualiserDecisionsDepuisApiChasque5Secondes();
}
