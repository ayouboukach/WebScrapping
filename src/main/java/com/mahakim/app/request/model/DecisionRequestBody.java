package com.mahakim.app.request.model;

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
public class DecisionRequestBody {

	@JsonProperty("idDecision")
	private int idDecision;
	
	@JsonProperty("dateDe")
	private String dateDe;
	
	@JsonProperty("dateNA")
	private String dateNA;
	
	@JsonProperty("dateTimeDecision")
	private String dateTimeDecision;

	@JsonProperty("typeDecision")
	private String typeDecision;
	
	@JsonProperty("contenuDecision")
	private String contenuDecision;
	
	@JsonProperty("numeroJugement")
	private String numeroJugement;
	
	@JsonProperty("dateTimeNextAudience")
	private String dateTimeNextAudience;
}
