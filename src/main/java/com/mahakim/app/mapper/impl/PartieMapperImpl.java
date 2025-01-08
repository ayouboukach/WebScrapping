package com.mahakim.app.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.mahakim.app.entities.PartieEntity;
import com.mahakim.app.entities.PartieEntity.PartieEntityBuilder;
import com.mahakim.app.mapper.PartieMapper;
import com.mahakim.app.request.model.PartieRequestBody;
import com.mahakim.app.request.model.PartieRequestBody.PartieRequestBodyBuilder;

import lombok.RequiredArgsConstructor;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2024-12-10T00:53:42+0100", comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)")
@Component
@RequiredArgsConstructor
public class PartieMapperImpl implements PartieMapper {

//	private final DossierMapper dossierMapper;
//	private final AvocatMapper avocatMapper;

	@Override
	public PartieEntity bodyToEntity(PartieRequestBody body) {
		if (body == null) {
			return null;
		}

		PartieEntityBuilder partieEntity = PartieEntity.builder();

		partieEntity.idPartie(body.getIdPartie());
		partieEntity.codeTypePersonne(body.getCodeTypePersonne());
		partieEntity.rolePartie(body.getRolePartie());
		partieEntity.nomPrenomPartie(body.getNomPrenomPartie());
		partieEntity.countAvocatsPartie(body.getCountAvocatsPartie());
		partieEntity.countHuissiersPartie(body.getCountHuissiersPartie());
		partieEntity.countMandatairesPartie(body.getCountMandatairesPartie());
		partieEntity.countRepresentantsPartie(body.getCountRepresentantsPartie());
//		partieEntity.dossier(dossierMapper.bodyToEntity(body.getDossier()));
//		partieEntity.avocats(avocatMapper.listBodyToEntity(
//				body.getAvocats().stream().map(AvocatRequestBody::getNomAvocatBarreau).collect(Collectors.toList())));

		return partieEntity.build();
	}

	@Override
	public PartieRequestBody entityToBody(PartieEntity entity) {
		if (entity == null) {
			return null;
		}

		PartieRequestBodyBuilder partieRequestBody = PartieRequestBody.builder();

		partieRequestBody.idPartie(entity.getIdPartie());
		partieRequestBody.codeTypePersonne(entity.getCodeTypePersonne());
		partieRequestBody.rolePartie(entity.getRolePartie());
		partieRequestBody.nomPrenomPartie(entity.getNomPrenomPartie());
		partieRequestBody.countAvocatsPartie(entity.getCountAvocatsPartie());
		partieRequestBody.countHuissiersPartie(entity.getCountHuissiersPartie());
		partieRequestBody.countMandatairesPartie(entity.getCountMandatairesPartie());
		partieRequestBody.countRepresentantsPartie(entity.getCountRepresentantsPartie());

		return partieRequestBody.build();
	}

	@Override
	public List<PartieEntity> listBodyToEntity(List<PartieRequestBody> bodyList) {
		if (bodyList == null) {
			return null;
		}

		List<PartieEntity> list = new ArrayList<PartieEntity>(bodyList.size());
		for (PartieRequestBody partieRequestBody : bodyList) {
			list.add(bodyToEntity(partieRequestBody));
		}

		return list;
	}

	@Override
	public List<PartieRequestBody> listEntityToBody(List<PartieEntity> entityList) {
		if (entityList == null) {
			return null;
		}

		List<PartieRequestBody> list = new ArrayList<PartieRequestBody>(entityList.size());
		for (PartieEntity partieEntity : entityList) {
			list.add(entityToBody(partieEntity));
		}

		return list;
	}
}
