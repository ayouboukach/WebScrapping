package com.mahakim.app.mapper;

import java.text.ParseException;
import java.util.List;

import org.mapstruct.Mapper;

import com.mahakim.app.request.model.DossierRequestBody;
import com.mahakim.app.entities.DossierEntity;

@Mapper(config = MapStructConfig.class)
public interface DossierMapper {

	DossierEntity bodyToEntity(DossierRequestBody body) throws ParseException;

	DossierRequestBody entityToBody(DossierEntity entity);

    List<DossierEntity> listBodyToEntity(List<DossierRequestBody> bodyList) throws ParseException;

    List<DossierRequestBody> listEntityToBody(List<DossierEntity> entityList);
}
