package com.mahakim.app.mapper;

import java.io.Serializable;

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
public class DossierEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8077449953901463124L;
	@JsonProperty("idDossierCivil")
	private int idDossierCivil;

	@JsonProperty("idDossierTF")
	private int idDossierTF;

	@JsonProperty("libEntite")
	private String libEntite;

	@JsonProperty("typeDossier")
	private String typeDossier;

	@JsonProperty("jugeRapporteur")
	private String jugeRapporteur;

	@JsonProperty("dateEnregistrementDossierDansRegistre")
	private String dateEnregistrementDossierDansRegistre;

	@JsonProperty("typeRequette")
	private String typeRequette;
	
	@JsonProperty("numeroCompletNationalDossier1Instance")
	private String numeroCompletNationalDossier1Instance;

	@JsonProperty("numeroCompletDossier1Instance")
	private String numeroCompletDossier1Instance;

	@JsonProperty("juridiction1Instance")
	private String juridiction1Instance;

	@JsonProperty("numeroCompletNationalDossier2Instance")
	private String numeroCompletNationalDossier2Instance;

	@JsonProperty("numeroCompletDossier2Instance")
	private String numeroCompletDossier2Instance;

	@JsonProperty("juridiction2Instance")
	private String juridiction2Instance;

	@JsonProperty("objetDossier")
	private String objetDossier;

	@JsonProperty("libelleDernierJugement")
	private String libelleDernierJugement;

	@JsonProperty("etatDernierJugement")
	private String etatDernierJugement;

	@JsonProperty("dateEtatJugementPret")
	private String dateEtatJugementPret;

	@JsonProperty("dateDernierJugement")
	private String dateDernierJugement;

	@JsonProperty("idDecisionDernierJugement")
	private int idDecisionDernierJugement;

	@JsonProperty("affaire")
	private String affaire;
}
