package com.mahakim.app.mapper;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Ayoub
 *
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DecisionEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6437925865348437399L;
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