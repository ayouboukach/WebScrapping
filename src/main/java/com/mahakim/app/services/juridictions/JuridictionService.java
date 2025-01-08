package com.mahakim.app.services.juridictions;

import java.util.List;

import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.JuridictionEntity;

public interface JuridictionService {

	
    void createJuridictions(String codeDossier);
    
    JuridictionEntity getJudirictionByName(String name);
    
    List<JuridictionEntity> getJuridictions();
    
    List<DossierEntity> actualiserJuridictionsDbPourDossiers(List<DossierEntity> dossiers);

	List<JuridictionEntity> getAllJuridictions();
}