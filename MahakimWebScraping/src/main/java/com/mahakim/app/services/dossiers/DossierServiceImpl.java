package com.mahakim.app.services.dossiers;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.JuridictionEntity;
import com.mahakim.app.mapper.DossierMapper;
import com.mahakim.app.repos.DossierRepo;
import com.mahakim.app.request.model.DossierRequestBody;
import com.mahakim.app.services.dossiers.decisions.DecisionService;
import com.mahakim.app.services.dossiers.parties.PartieService;
import com.mahakim.app.services.juridictions.JuridictionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DossierServiceImpl implements DossierService {

	private final DossierRepo dossierRepo;
	private final DossierMapper dossierMapper;
	private final DecisionService decisionService;
	private final JuridictionService juridictionService;
	private final APIClientService apiClientService;
	private final PartieService partieService;
	private JuridictionEntity juridiction1InstanceEntity;
	private JuridictionEntity juridiction2InstanceEntity;

	@Override
	public List<DossierEntity> createDossiers(String year, String codeDossier, String StartWithNumeroDossier, int Range,
			int idJuidiction) throws ParseException, StreamReadException, DatabindException, IOException {
//		RespenseRecevied request;
//		DossierRequestBody ds;
		List<DossierRequestBody> dossiers = new ArrayList<DossierRequestBody>();
//		List<DossierEntity> dossierEntities = new ArrayList<DossierEntity>();
		try {
			for (int i = 0; i < Range; i++) {
				dossiers.add(apiClientService.getDossierByNumeroComplet(
						year.concat(codeDossier)
								.concat(Integer.toString((Integer.parseInt(StartWithNumeroDossier) + i))),
						Integer.toString(idJuidiction)));
			}
			List<DossierEntity> dossierEntities= dossierRepo.saveAll(dossiers.stream().peek(d -> {
				juridiction1InstanceEntity = juridictionService.getJudirictionByName(d.getJuridiction1Instance());
				juridiction2InstanceEntity = juridictionService.getJudirictionByName(d.getJuridiction2Instance());
			}).map(de -> {
				try {
					return dossierMapper.bodyToEntity(de);
				} catch (ParseException e) { // TODO Auto-generated catch block e.printStackTrace();
				}
				return null;
			}).peek(dee -> {
				dee.setJuridiction1Instance(juridiction1InstanceEntity);
				dee.setJuridiction2Instance(juridiction2InstanceEntity);
				try {
					dee.setDecisions(decisionService.refreshDecisionsByDossierFromAPI(dee).stream()
							.peek(dec -> dec.setDossier(dee))
							.sorted(Comparator.comparing(DecisionEntity::getDateTimeDecision))
							.collect(Collectors.toList()));
//					dee.setParties(partieService.refreshPartiesByIdDossierFromAPI(dee.getIdDossierCivil()));
				} catch (ParseException e) { // TODO Auto-generated
					e.printStackTrace();
				}
			}).collect(Collectors.toList()));
			
			dossierEntities.stream().forEach(d->{
				try {
					partieService.refreshPartiesByDossierFromAPI(d);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			log.debug("ok");
			log.debug("ok");
			log.debug("ok");

		} catch (Exception e) {

		}
		return null;
	}

	@SuppressWarnings("unused")
	private String getPath(String year, String codeDossier, String StartWithNumeroDossier, int i) {
		return System.getProperty("user.home") + "/Desktop/ListeDossiers/"
				+ year.concat(codeDossier).concat(Integer.toString((Integer.parseInt(StartWithNumeroDossier) + i)));
	}

	@Override
	public DossierRequestBody getDossierById() {
		// TODO Auto-generated method stub
		return null;
	}
}
