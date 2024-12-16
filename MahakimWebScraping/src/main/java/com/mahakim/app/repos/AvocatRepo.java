package com.mahakim.app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mahakim.app.entities.AvocatEntity;

public interface AvocatRepo extends JpaRepository<AvocatEntity , Integer> {

	AvocatEntity findByNomAvocatBarreau(String name);
	
//	@Query("select count(e)>0 from MyEntity e where ...")
	boolean existsByNomAvocatBarreau(String name);
}
