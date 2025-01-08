package com.mahakim.app.client;

import static com.mahakim.app.constant.APIKeyConstant.CODE_DOSSIER_KEY;
import static com.mahakim.app.constant.APIKeyConstant.ID_DOSSIER_KEY;
import static com.mahakim.app.constant.APIKeyConstant.ID_JURIDICTION_2INSTANCE;
import static com.mahakim.app.constant.APIKeyConstant.ID_JURIDICTION_KEY;
import static com.mahakim.app.constant.APIKeyConstant.ID_PARTIE_KEY;
import static com.mahakim.app.constant.APIKeyConstant.NUMERO_COMPLET_DOSSIER_KEY;
import static com.mahakim.app.constant.APIKeyConstant.TYPE_AFFAIRE_KEY;
import static com.mahakim.app.constant.APIKeyConstant.TYPE_PERSONNE;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mahakim.app.exception.exceptions.ErrorDataLoadingException;
import com.mahakim.app.request.model.DecisionRequestBody;
import com.mahakim.app.request.model.DossierRequestBody;
import com.mahakim.app.request.model.JuridictionRequestBody;
import com.mahakim.app.request.model.PartieRequestBody;
import com.mahakim.app.request.model.RespenseRecevied;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class APIClientService {

	@Value("${liste.Juridictions.2Instance.path}")
	private String listJuridictions2Path;
	@Value("${liste.Juridictions.1Instance.path}")
	private String listJuridictions1Path;
	@Value("${liste.Dicisions.path}")
	private String listDecisionsPath;
	@Value("${carteDossier.path}")
	private String dossierPath;
	@Value("${liste.parties}")
	private String partiePath;
	@Value("${liste.avocats}")
	private String avocatsPath;
	
	RespenseRecevied request;
	private final ObjectMapper mapper = new ObjectMapper();
	private final WebClient webClient;

	private void sleepFor3Seconds() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt(); // Restore interrupted status
			throw new RuntimeException("Thread was interrupted while sleeping.", e);
		}
	}

	public List<JuridictionRequestBody> getJuridictions2InstanceByCodeDossier(String codeDossier) {
		sleepFor3Seconds(); // Add sleep here

		List<JuridictionRequestBody> juridictions = null;
		request = webClient.get()
				.uri(listJuridictions2Path,
						uri -> uri.queryParam(CODE_DOSSIER_KEY, codeDossier).build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				juridictions = mapper.convertValue(request.getData(), new TypeReference<List<JuridictionRequestBody>>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading juridictions from API");
		}

		return juridictions;
	}

	public List<JuridictionRequestBody> getJuridictions1InstanceByCodeDossierAndParentJuridiction(String codeDossier, String idJuridiction) {
		sleepFor3Seconds(); // Add sleep here

		List<JuridictionRequestBody> juridictions = null;
		request = webClient.get()
				.uri(listJuridictions1Path,
						uri -> uri.queryParam(CODE_DOSSIER_KEY, codeDossier)
						.queryParam(ID_JURIDICTION_2INSTANCE, idJuridiction)
						.build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				juridictions = mapper.convertValue(request.getData(), new TypeReference<List<JuridictionRequestBody>>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading juridictions from API");
		}

		return juridictions;
	}

	public List<DecisionRequestBody> rechercherDecisionsPourIdDossier(String idDossier, String typeAffaire) {
		sleepFor3Seconds(); // Add sleep here

		List<DecisionRequestBody> decisions = null;
		request = webClient.get()
				.uri(listDecisionsPath,
						uri -> uri.queryParam(ID_DOSSIER_KEY, idDossier)
						.queryParam(TYPE_AFFAIRE_KEY, typeAffaire)
						.build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				decisions = mapper.convertValue(request.getData(), new TypeReference<List<DecisionRequestBody>>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading decisions from API");
		}

		return decisions;
	}

	public DossierRequestBody getDossierByNumeroComplet(String numeroComplet, String idJuridiction) {
		sleepFor3Seconds(); // Add sleep here

		DossierRequestBody dossier = null;
		request = webClient.get()
				.uri(dossierPath,
						uri -> uri.queryParam(NUMERO_COMPLET_DOSSIER_KEY, numeroComplet)
						.queryParam(ID_JURIDICTION_KEY, idJuridiction)
						.build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				dossier = mapper.convertValue(request.getData(), new TypeReference<DossierRequestBody>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading dossier from API");
		}

		return dossier;
	}

	public List<PartieRequestBody> getPartiesByIdDossier(String idDossier, String typeAffaire) {
		sleepFor3Seconds(); // Add sleep here

		List<PartieRequestBody> parties = null;
		request = webClient.get()
				.uri(partiePath,
						uri -> uri.queryParam(ID_DOSSIER_KEY, idDossier)
						.queryParam(TYPE_AFFAIRE_KEY, typeAffaire)
						.build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				parties = mapper.convertValue(request.getData(), new TypeReference<List<PartieRequestBody>>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading parties from API");
		}

		return parties;
	}

	public List<String> getAvocatsByIdPartie(String idPartie, String typePersonne, String typeAffaire) {
		sleepFor3Seconds(); // Add sleep here

		List<String> parties = null;
		request = webClient.get()
				.uri(avocatsPath,
						uri -> uri.queryParam(ID_PARTIE_KEY, idPartie)
						.queryParam(TYPE_AFFAIRE_KEY, typeAffaire)
						.queryParam(TYPE_PERSONNE, typePersonne)
						.build())
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
				.bodyToMono(RespenseRecevied.class).block();
		try {
			if (request != null)
				parties = mapper.convertValue(request.getData(), new TypeReference<List<String>>() {
				});
		} catch (Exception e) {
			throw new ErrorDataLoadingException("Data not mapped successfully will loading avocats from API");
		}

		return parties;
	}
}
