package com.mahakim.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahakim.app.entities.DossierEntity;

public interface DossierRepo extends JpaRepository<DossierEntity , Integer> {

}
