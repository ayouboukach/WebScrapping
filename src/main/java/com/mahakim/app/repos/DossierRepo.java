package com.mahakim.app.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;

public interface DossierRepo extends JpaRepository<DossierEntity , Integer> {

    Optional<DossierEntity> findByIdDossierCivil(int idDossierCivil);
    
    @Query("SELECT d FROM DossierEntity d JOIN d.decisions de WHERE de IS NOT NULL")
    List<DossierEntity> findAllDossiersWithDecisions();
    
    @Query("SELECT DISTINCT d FROM DossierEntity d JOIN d.decisions de WHERE de IN :decisions")
    List<DossierEntity> findDossiersByDecisionsIn(@Param("decisions") List<DecisionEntity> decisions);
}
