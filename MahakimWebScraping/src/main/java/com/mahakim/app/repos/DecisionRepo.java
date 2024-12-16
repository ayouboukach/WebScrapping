package com.mahakim.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;

public interface DecisionRepo extends JpaRepository<DecisionEntity , Integer> {

	List<DecisionEntity> findByDossier(DossierEntity dossier);
}
