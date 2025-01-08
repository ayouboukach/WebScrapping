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
public class PartieRequestBody {

	@JsonProperty("idPartie")
	private int idPartie;

	@JsonProperty("codeTypePersonne")
	private String codeTypePersonne;

	@JsonProperty("rolePartie")
	private String rolePartie;

	@JsonProperty("nomPrenomPartie")
	private String nomPrenomPartie;

	@JsonProperty("countAvocatsPartie")
	private int countAvocatsPartie;

	@JsonProperty("countHuissiersPartie")
	private int countHuissiersPartie;

	@JsonProperty("countMandatairesPartie")
	private int countMandatairesPartie;
	
	@JsonProperty("countRepresentantsPartie")
	private int countRepresentantsPartie;
	
//	@JsonProperty("dossier")
	private DossierRequestBody dossier;
	
//	@JsonProperty("avocats")
	private List<AvocatRequestBody> avocats;
}
