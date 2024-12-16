package com.mahakim.app.services.dossiers.parties;

import java.text.ParseException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.AvocatEntity;
import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.PartieEntity;
import com.mahakim.app.mapper.AvocatMapper;
import com.mahakim.app.mapper.PartieMapper;
import com.mahakim.app.repos.PartieRepo;
import com.mahakim.app.request.model.PartieRequestBody;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
//@Slf4j
public class PartieServiceImpl implements PartieService {

	private final APIClientService apiClientService;
	private final PartieMapper partieMapper;
	private final PartieRepo partieRepo;
	private final AvocatMapper avocatMapper;

	@Override
	public void createParties() {
		// TODO Auto-generated method stub

	}

	@Override
	public PartieEntity getPartieById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PartieEntity> getPartiesByDossier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<PartieEntity> refreshPartiesByDossierFromAPI(DossierEntity dossier) throws ParseException {
		List<PartieRequestBody> parties = apiClientService
				.getPartiesByIdDossier(Integer.toString(dossier.getIdDossierCivil()), "DC");
		Set<AvocatEntity> entities = parties.stream().map(partieMapper::bodyToEntity).peek(p -> {
			p.setAvocats(apiClientService
					.getAvocatsByIdPartie(String.valueOf(p.getIdPartie()), p.getCodeTypePersonne(), "DC").stream()
					.map(avocatMapper::bodyToEntity).peek(a -> a.setParties(Set.of(p))).collect(Collectors.toSet()));
			p.setDossier(dossier);
		}).map(PartieEntity::getAvocats).flatMap(Collection::stream).collect(Collectors.toSet());
		List<PartieEntity> justPartiesWithAvocats = entities.stream().map(AvocatEntity::getParties)
				.flatMap(Collection::stream).distinct().collect(Collectors.toList());
		List<PartieEntity> partiesSaved = partieMapper.listBodyToEntity(parties).stream()
				.peek(p -> p.setDossier(dossier)).collect(Collectors.toList());
		partiesSaved.addAll(justPartiesWithAvocats);
		return new HashSet<>(partieRepo.saveAll(partiesSaved));
	}

	@Override
	public List<PartieEntity> getPartiesByIdDossierFromAPI(int id) throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
}
