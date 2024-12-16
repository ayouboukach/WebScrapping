package com.mahakim.app.services.avocats;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.entities.AvocatEntity;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
//@Slf4j
public class AvocatServiceImpl implements AvocatService{

	
	@Override
	public Map<String, List<AvocatEntity>> refreshAvocatsWithIdPartie(Map<String , List<AvocatEntity>> avocatEntities) {
		return avocatEntities;

	}
	
}
