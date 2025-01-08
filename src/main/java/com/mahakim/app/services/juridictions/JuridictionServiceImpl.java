package com.mahakim.app.services.juridictions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.JuridictionEntity;
import com.mahakim.app.exception.exceptions.NotFoundException;
import com.mahakim.app.mapper.JuridictionMapper;
import com.mahakim.app.repos.JuridictionRepo;
import com.mahakim.app.request.model.JuridictionRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
//@Slf4j
public class JuridictionServiceImpl implements JuridictionService {

	private final JuridictionRepo juridictionRepo;
	private final APIClientService apiClientService;
	private final JuridictionMapper juridictionMapper;

	@Override
	public void createJuridictions(String codeDossier) {
		List<JuridictionRequestBody> juridictions = chercherJuridictionsAPI(codeDossier, null);
		if (juridictions == null || juridictions.isEmpty()) {
			throw new NotFoundException("Aucune juridictions trouvée par code dossier: " + codeDossier);
		}
		List<JuridictionEntity> toutesJuridictions = new ArrayList<>();
		toutesJuridictions.addAll(juridictionMapper.listBodyToEntity(juridictions));
		juridictions.forEach(j -> {
			List<JuridictionRequestBody> JuridictionsFils = chercherJuridictionsAPI(codeDossier,
					Integer.toString(j.getIdJuridiction()));
			if (JuridictionsFils != null && !JuridictionsFils.isEmpty()) {
				toutesJuridictions.addAll(juridictionMapper.listBodyToEntity(JuridictionsFils).stream()
						.peek(ju -> ju.setJuridictionParent(juridictionMapper.bodyToEntity(j)))
						.collect(Collectors.toList()));
			}
		});
		juridictionRepo.saveAll(toutesJuridictions);
	}
	
	@Override
	public List<DossierEntity> actualiserJuridictionsDbPourDossiers(List<DossierEntity> dossiers) {
		List<JuridictionEntity> listeJuridictions = getAllJuridictions();
		Map<String, JuridictionEntity> juridictionMap = listeJuridictions.stream()
				.collect(Collectors.toMap(JuridictionEntity::getNomJuridiction, juridiction -> juridiction));
		dossiers.forEach(dossier -> {
			Optional.ofNullable(juridictionMap.get(dossier.getJuridiction1InstanceName()))
					.ifPresent(dossier::setJuridiction1Instance);
			Optional.ofNullable(juridictionMap.get(dossier.getJuridiction2InstanceName()))
					.ifPresent(dossier::setJuridiction2Instance);
		});
		return dossiers;
	}

	@Override
	@Cacheable(value = "juridictionsCache", key = "'juridictionsList'")
	public List<JuridictionEntity> getAllJuridictions() {
		return juridictionRepo.findAll();
	}

	@Override
	public JuridictionEntity getJudirictionByName(String name) {
		return juridictionRepo.findByNomJuridiction(name);
	}

	@Override
	public List<JuridictionEntity> getJuridictions() {
		return juridictionRepo.findAll();
	}

	private List<JuridictionRequestBody> chercherJuridictionsAPI(String codeDossier, String idJuridiction) {
		return idJuridiction == null ? apiClientService.getJuridictions2InstanceByCodeDossier(codeDossier)
				: apiClientService.getJuridictions1InstanceByCodeDossierAndParentJuridiction(codeDossier,
						idJuridiction);

	}
}
