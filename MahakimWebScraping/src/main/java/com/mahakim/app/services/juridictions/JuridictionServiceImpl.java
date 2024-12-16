package com.mahakim.app.services.juridictions;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.JuridictionEntity;
import com.mahakim.app.exception.exceptions.NotFoundException;
import com.mahakim.app.mapper.JuridictionMapper;
import com.mahakim.app.repos.JuridictionRepo;
import com.mahakim.app.request.model.JuridictionRequestBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class JuridictionServiceImpl implements JuridictionService {

	private final JuridictionRepo juridictionRepo;
	private final APIClientService apiClientService;
	private final JuridictionMapper juridictionMapper;

	@Override
	public void createJuridictions(String codeDossier) {
		List<JuridictionRequestBody> juridictions = getJuridictionFromAPI(codeDossier, null);
//		juridictions.stream().map(juridictionMapper::bodyToEntity)
//				.filter(j -> !juridictionRepo.findAll().contains(j))
//				.findAny()
//				.ifPresentOrElse(j -> {
//					juridictionRepo.save(j);
//				}, () -> {
//					throw new NotFoundException("No Juridictions found by code dossier: " + codeDossier);
//				});
		if (juridictions != null) {
			Optional.of(juridictions).ifPresentOrElse(js -> {
				juridictionRepo.saveAll(juridictionMapper.listBodyToEntity(juridictions));
				juridictions.stream().forEach(j -> {
					juridictionRepo.saveAll(juridictionMapper
							.listBodyToEntity(
									getJuridictionFromAPI(codeDossier, Integer.toString(j.getIdJuridiction())))
							.stream().peek(ju -> {
								ju.setJuridictionParent(juridictionMapper.bodyToEntity(j));
							}).collect(Collectors.toList()));
				});
			}, () -> {
				throw new NotFoundException("No Juridictions found by code dossier: " + codeDossier);
			});
		} else {
			throw new NotFoundException("No Juridictions found by code dossier: " + codeDossier);
		}

		log.debug("ok");
		log.debug("ok");

	}

	private List<JuridictionRequestBody> getJuridictionFromAPI(String codeDossier, String idJuridiction) {
		return idJuridiction == null ? apiClientService.getJuridictions2InstanceByCodeDossier(codeDossier)
				: apiClientService.getJuridictions1InstanceByCodeDossierAndParentJuridiction(codeDossier,
						idJuridiction);

	}

	@Override
	public JuridictionEntity getJudirictionByName(String name) {
		// TODO Auto-generated method stub
		return juridictionRepo.findByNomJuridiction(name);
	}

	@Override
	public List<JuridictionEntity> getJuridictions() {
		return juridictionRepo.findAll();
	}
}
