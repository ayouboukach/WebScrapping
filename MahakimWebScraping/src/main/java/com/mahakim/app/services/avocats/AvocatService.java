package com.mahakim.app.services.avocats;

import java.util.List;
import java.util.Map;

import com.mahakim.app.entities.AvocatEntity;

public interface AvocatService {

	Map<String, List<AvocatEntity>> refreshAvocatsWithIdPartie(Map<String, List<AvocatEntity>> avocatEntities);

	
}
