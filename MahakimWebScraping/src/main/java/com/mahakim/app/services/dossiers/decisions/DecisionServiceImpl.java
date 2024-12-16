package com.mahakim.app.services.dossiers.decisions;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.mapper.DecisionMapper;
import com.mahakim.app.repos.DecisionRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
//@Slf4j
public class DecisionServiceImpl implements DecisionService {

	private final DecisionRepo decisionRepo;
	private final DecisionMapper decisionMapper;
	private final APIClientService apiClientService;

	@Override
	public void createDecisions() {
		// TODO Auto-generated method stub

	}

	@Override
	public DecisionEntity getDecisionById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DecisionEntity> getDecisionsByDossier() {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	public List<DecisionEntity> refreshDecisionsByDossierFromAPI(DossierEntity dossier) throws ParseException {
//		List<DecisionEntity> listDecisionFromApi = decisionMapper
//				.listBodyToEntity(apiClientService.getDecisionsByIdDossier(Integer.toString(dossier.getIdDossierCivil()), "DC"));
//		List<DecisionEntity> listDecisionFromDb = decisionRepo.findByDossier(dossier);
		return decisionMapper.listBodyToEntity(
				apiClientService.getDecisionsByIdDossier(Integer.toString(dossier.getIdDossierCivil()), "DC"));
	}

	@Override
	public List<DecisionEntity> saveDecisionsByIdDossierFromAPI(int id) throws ParseException {
		List<DecisionEntity> decisionEntities = decisionMapper
				.listBodyToEntity(apiClientService.getDecisionsByIdDossier(Integer.toString(id), "DC"));
		return decisionRepo.saveAll(decisionEntities);
	}
}
