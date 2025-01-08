package com.mahakim.app.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.mahakim.app.entities.PartieEntity;
import com.mahakim.app.request.model.PartieRequestBody;

@Mapper(config = MapStructConfig.class)
public interface PartieMapper {

	PartieEntity bodyToEntity(PartieRequestBody body);

	PartieRequestBody entityToBody(PartieEntity entity);

    List<PartieRequestBody> listEntityToBody(List<PartieEntity> entityList);

	List<PartieEntity> listBodyToEntity(List<PartieRequestBody> bodyList);
}
