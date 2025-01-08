package com.mahakim.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahakim.app.entities.JuridictionEntity;

public interface JuridictionRepo extends JpaRepository<JuridictionEntity , Integer> {
	
	JuridictionEntity findByNomJuridiction(String name);

}
