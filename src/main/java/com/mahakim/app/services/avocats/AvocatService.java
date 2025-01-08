package com.mahakim.app.services.avocats;

import java.util.List;

import com.mahakim.app.entities.PartieEntity;

public interface AvocatService {

	List<PartieEntity> actualiserAvocatsPourParties(List<PartieEntity> parties);

	
}
