package com.mahakim.app.services.juridictions;

import java.util.List;

import com.mahakim.app.entities.JuridictionEntity;

public interface JuridictionService {

	
    void createJuridictions(String codeDossier);
    
    JuridictionEntity getJudirictionByName(String name);
    
    List<JuridictionEntity> getJuridictions();
}
