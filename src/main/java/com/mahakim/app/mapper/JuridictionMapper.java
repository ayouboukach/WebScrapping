package com.mahakim.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mahakim.app.request.model.JuridictionRequestBody;

@Mapper(config = MapStructConfig.class)
public interface JuridictionMapper {

	com.mahakim.app.entities.JuridictionEntity bodyToEntity(JuridictionRequestBody body);

	JuridictionRequestBody entityToBody(com.mahakim.app.entities.JuridictionEntity entity);

    List<com.mahakim.app.entities.JuridictionEntity> listBodyToEntity(List<JuridictionRequestBody> bodyList);

    List<JuridictionRequestBody> listEntityToBody(List<com.mahakim.app.entities.JuridictionEntity> entityList);
}
