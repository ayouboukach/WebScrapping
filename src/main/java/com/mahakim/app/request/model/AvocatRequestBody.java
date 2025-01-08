package com.mahakim.app.request.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AvocatRequestBody {

	@JsonProperty("idAvocat")
	private int idAvocat;

	@JsonProperty("codeTypePersonne")
	private String nomAvocatBarreau;
	
	@JsonProperty("parties")	
	private List<PartieRequestBody> parties;
}
