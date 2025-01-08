package com.mahakim.app.repos;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mahakim.app.entities.AvocatEntity;

public interface AvocatRepo extends JpaRepository<AvocatEntity, Integer> {

    @Query("SELECT a FROM AvocatEntity a WHERE a.nomAvocatBarreau = :nomAvocatBarreau")
    AvocatEntity findByNomAvocatBarreau(@Param("nomAvocatBarreau") String nomAvocatBarreau);
    
    List<AvocatEntity> findByNomAvocatBarreauIn(Set<String> nomAvocatBarreau);	
}