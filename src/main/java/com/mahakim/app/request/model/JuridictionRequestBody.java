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
public class JuridictionRequestBody {

	@JsonProperty("idJuridiction")
	private int idJuridiction;

	@JsonProperty("nomJuridiction")
	private String nomJuridiction;

	@JsonProperty("idJuridictionParent")
	private int idJuridictionParent;

	@JsonProperty("typeJuridication")
	private String typeJuridication;
	
	@JsonProperty("urlJuridiction")
	private String urlJuridiction;
	
	@JsonProperty("codeJuridiction")
	private String codeJuridiction;
	
	@JsonProperty("urlSajExtension")
	private String urlSajExtension;
}
