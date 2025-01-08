package com.mahakim.app.mapper.impl;

import com.mahakim.app.entities.JuridictionEntity;
import com.mahakim.app.mapper.JuridictionMapper;
import com.mahakim.app.request.model.JuridictionRequestBody;
import com.mahakim.app.request.model.JuridictionRequestBody.JuridictionRequestBodyBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2024-12-04T07:17:27+0100", comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)")
@Component
public class JuridictionMapperImpl implements JuridictionMapper {

	@Override
	public JuridictionEntity bodyToEntity(JuridictionRequestBody body) {
		if (body == null) {
			return null;
		}

		JuridictionEntity juridictionEntity = new JuridictionEntity();

		juridictionEntity.setIdJuridiction(body.getIdJuridiction());
		juridictionEntity.setNomJuridiction(body.getNomJuridiction());
//        juridictionEntity.setIdJuridictionParent( body.getIdJuridictionParent() );
		juridictionEntity.setTypeJuridication(body.getTypeJuridication());
		juridictionEntity.setUrlJuridiction(body.getUrlJuridiction());
		juridictionEntity.setCodeJuridiction(body.getCodeJuridiction());
		juridictionEntity.setUrlSajExtension(body.getUrlSajExtension());

		return juridictionEntity;
	}

	@Override
	public JuridictionRequestBody entityToBody(JuridictionEntity entity) {
		if (entity == null) {
			return null;
		}

		JuridictionRequestBodyBuilder juridictionRequestBody = JuridictionRequestBody.builder();

		juridictionRequestBody.idJuridiction(entity.getIdJuridiction());
		juridictionRequestBody.nomJuridiction(entity.getNomJuridiction());
		if (entity.getJuridictionParent() != null)
			juridictionRequestBody.idJuridictionParent(entity.getJuridictionParent().getIdJuridiction());
		juridictionRequestBody.typeJuridication(entity.getTypeJuridication());
		juridictionRequestBody.urlJuridiction(entity.getUrlJuridiction());
		juridictionRequestBody.codeJuridiction(entity.getCodeJuridiction());
		juridictionRequestBody.urlSajExtension(entity.getUrlSajExtension());

		return juridictionRequestBody.build();
	}

	@Override
	public List<JuridictionEntity> listBodyToEntity(List<JuridictionRequestBody> bodyList) {
		if (bodyList == null) {
			return null;
		}

		List<JuridictionEntity> list = new ArrayList<JuridictionEntity>(bodyList.size());
		for (JuridictionRequestBody juridictionRequestBody : bodyList) {
			list.add(bodyToEntity(juridictionRequestBody));
		}

		return list;
	}

	@Override
	public List<JuridictionRequestBody> listEntityToBody(List<JuridictionEntity> entityList) {
		if (entityList == null) {
			return null;
		}

		List<JuridictionRequestBody> list = new ArrayList<JuridictionRequestBody>(entityList.size());
		for (JuridictionEntity juridictionEntity : entityList) {
			list.add(entityToBody(juridictionEntity));
		}

		return list;
	}
}
