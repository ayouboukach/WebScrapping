package com.mahakim.app.mapper;

import java.text.ParseException;
import java.util.List;

import org.mapstruct.Mapper;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.request.model.DecisionRequestBody;

@Mapper(config = MapStructConfig.class)
public interface DecisionMapper {

	DecisionEntity bodyToEntity(DecisionRequestBody body) throws ParseException;

	DecisionRequestBody entityToBody(DecisionEntity entity);

    List<DecisionRequestBody> listEntityToBody(List<DecisionEntity> entityList);

	List<DecisionEntity> listBodyToEntity(List<DecisionRequestBody> bodyList) throws ParseException;
}
