package com.mahakim.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mahakim.app.entities.AvocatEntity;
import com.mahakim.app.request.model.AvocatRequestBody;

@Mapper(config = MapStructConfig.class)
public interface AvocatMapper {

	AvocatEntity bodyToEntity(String body);

	AvocatRequestBody entityToBody(AvocatEntity entity);

    List<AvocatEntity> listBodyToEntity(List<String> bodyList);

    List<AvocatRequestBody> listEntityToBody(List<AvocatEntity> entityList);
}
