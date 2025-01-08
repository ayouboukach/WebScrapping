package com.mahakim.app.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.mahakim.app.entities.AvocatEntity;
import com.mahakim.app.entities.AvocatEntity.AvocatEntityBuilder;
import com.mahakim.app.mapper.AvocatMapper;
import com.mahakim.app.request.model.AvocatRequestBody;
import com.mahakim.app.request.model.AvocatRequestBody.AvocatRequestBodyBuilder;

import lombok.RequiredArgsConstructor;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2024-12-10T00:53:42+0100", comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.9 (Oracle Corporation)")
@Component
@RequiredArgsConstructor
public class AvocatMapperImpl implements AvocatMapper {

//	private final PartieMapper partieMapper;

	@Override
	public AvocatEntity bodyToEntity(String body) {
		if (body == null) {
			return null;
		}

		AvocatEntityBuilder avocatEntity = AvocatEntity.builder().nomAvocatBarreau(body);

		return avocatEntity.build();
	}

	@Override
	public AvocatRequestBody entityToBody(AvocatEntity entity) {
		if (entity == null) {
			return null;
		}

		AvocatRequestBodyBuilder avocatRequestBody = AvocatRequestBody.builder()
				.nomAvocatBarreau(entity.getNomAvocatBarreau())
//				.idAvocat(entity.getIdAvocat())
//				.parties(partieMapper.listEntityToBody(entity.getParties()))
				;

		return avocatRequestBody.build();
	}

	@Override
	public List<AvocatEntity> listBodyToEntity(List<String> bodyList) {
		if (bodyList == null) {
			return null;
		}

		List<AvocatEntity> list = new ArrayList<AvocatEntity>(bodyList.size());
		for (String string : bodyList) {
			list.add(bodyToEntity(string));
		}

		return list;
	}

	@Override
	public List<AvocatRequestBody> listEntityToBody(List<AvocatEntity> entityList) {
		if (entityList == null) {
			return null;
		}

		List<AvocatRequestBody> list = new ArrayList<AvocatRequestBody>(entityList.size());
		for (AvocatEntity avocatEntity : entityList) {
			list.add(entityToBody(avocatEntity));
		}

		return list;
	}
}
