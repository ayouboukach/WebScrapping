package com.mahakim.app.mapper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DecisionEntity.DecisionEntityBuilder;
import com.mahakim.app.mapper.DecisionMapper;
import com.mahakim.app.request.model.DecisionRequestBody;
import com.mahakim.app.request.model.DecisionRequestBody.DecisionRequestBodyBuilder;

@Generated(value = "org.mapstruct.ap.MappingProcessor", date = "2024-12-04T07:17:27+0100", comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)")
@Component
public class DecisionMapperImpl implements DecisionMapper {

	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm");
	SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public DecisionEntity bodyToEntity(DecisionRequestBody body) throws ParseException {
		if (body == null) {
			return null;
		}
		DecisionEntityBuilder decisionEntity = DecisionEntity.builder();
		decisionEntity.idDecision(body.getIdDecision());
		if (body.getDateDe() != null)
			decisionEntity.dateDe(formatter.parse(body.getDateDe()));
		if (body.getDateNA() != null)
			decisionEntity.dateNA(formatter.parse(body.getDateNA()));
		if (body.getDateTimeDecision() != null) {
			if (body.getDateTimeDecision().matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]{2}):([0-9]{2})"))
			decisionEntity.dateTimeDecision(formatter.parse(body.getDateTimeDecision()));
		else if (body.getDateTimeDecision().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
			decisionEntity.dateTimeDecision(formatter2.parse(body.getDateTimeDecision()));
		}
		decisionEntity.typeDecision(body.getTypeDecision());
		decisionEntity.contenuDecision(body.getContenuDecision());
		decisionEntity.numeroJugement(body.getNumeroJugement());
		if (body.getDateTimeNextAudience() != null) {
			if (body.getDateTimeNextAudience().matches("([0-9]{2})/([0-9]{2})/([0-9]{4}) ([0-9]{2}):([0-9]{2})"))
			decisionEntity.dateTimeNextAudience(formatter.parse(body.getDateTimeNextAudience()));
		else if (body.getDateTimeNextAudience().matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
			decisionEntity.dateTimeNextAudience(formatter2.parse(body.getDateTimeNextAudience()));
		}
		return decisionEntity.build();
	}

	@Override
	public DecisionRequestBody entityToBody(DecisionEntity entity) {
		if (entity == null) {
			return null;
		}

		DecisionRequestBodyBuilder decisionRequestBody = DecisionRequestBody.builder();

		decisionRequestBody.idDecision(entity.getIdDecision());
		decisionRequestBody.dateDe(formatter.format(entity.getDateDe()));
		decisionRequestBody.dateNA(formatter.format(entity.getDateNA()));
		decisionRequestBody.dateTimeDecision(formatter.format(entity.getDateTimeDecision()));
		decisionRequestBody.typeDecision(entity.getTypeDecision());
		decisionRequestBody.contenuDecision(entity.getContenuDecision());
		decisionRequestBody.numeroJugement(entity.getNumeroJugement());
		decisionRequestBody.dateTimeNextAudience(formatter.format(entity.getDateTimeNextAudience()));

		return decisionRequestBody.build();
	}

	@Override
	public List<DecisionEntity> listBodyToEntity(List<DecisionRequestBody> bodyList) throws ParseException {
		if (bodyList == null) {
			return null;
		}

		List<DecisionEntity> list = new ArrayList<DecisionEntity>(bodyList.size());
		for (DecisionRequestBody decisionRequestBody : bodyList) {
			list.add(bodyToEntity(decisionRequestBody));
		}

		return list;
	}

	@Override
	public List<DecisionRequestBody> listEntityToBody(List<DecisionEntity> entityList) {
		if (entityList == null) {
			return null;
		}

		List<DecisionRequestBody> list = new ArrayList<DecisionRequestBody>(entityList.size());
		for (DecisionEntity decisionEntity : entityList) {
			list.add(entityToBody(decisionEntity));
		}

		return list;
	}
}
