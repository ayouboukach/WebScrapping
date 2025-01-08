package com.mahakim.app.helpers;

import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.DecisionEntity;

import java.util.*;
import java.util.stream.Collectors;

public class DossierHelper {

	public static Map<DossierEntity, List<DecisionEntity>> convertirDossiersEtDecisionsVersMap(
	        List<DecisionEntity> decisions, List<DossierEntity> dossiers) {
	    
	    if (dossiers == null) {
	        dossiers = decisions.stream()
	                            .map(DecisionEntity::getDossier)
	                            .filter(Objects::nonNull)
	                            .distinct()
	                            .collect(Collectors.toList());
	    }

	    Map<DossierEntity, List<DecisionEntity>> dossierDecisionsMapes = new HashMap<>();
	    
	    dossiers.forEach(dossier -> dossierDecisionsMapes.putIfAbsent(dossier, new ArrayList<>()));
	    
	    decisions.forEach(decision -> Optional.ofNullable(decision.getDossier())
	            .ifPresent(dossier -> dossierDecisionsMapes
	                    .computeIfAbsent(dossier, k -> new ArrayList<>())
	                    .add(decision)));
	    
	    return dossierDecisionsMapes;
	}


    public static void mergeEntreDossierEtLeurDecisions(DossierEntity dossier, List<DecisionEntity> DecisionsActualisees) {
        dossier.setDecisions(DecisionsActualisees);
        DecisionsActualisees.forEach(decision -> decision.setDossier(dossier));
    }

    public static List<DossierEntity> convertirMapVersDossiers(Map<DossierEntity, List<DecisionEntity>> DecisionsGroupeePourDossier) {
        return DecisionsGroupeePourDossier.entrySet().stream()
                .map(entry -> {
                    DossierEntity dossier = entry.getKey();
                    List<DecisionEntity> decisions = entry.getValue();
                    mergeEntreDossierEtLeurDecisions(dossier, decisions);
                    return dossier;
                })
                .collect(Collectors.toList());
    }
    
    public static Map<DossierEntity, List<DecisionEntity>> convertirDossiersVersMap(List<DossierEntity> dossiers) {
        return dossiers.stream()
            .collect(Collectors.toMap(
                dossier -> dossier,
                DossierEntity::getDecisions
            ));
    }
    
    
    public static List<DossierEntity> convertirDecisionsEnDossiers(List<DecisionEntity> decisions) {
        Set<DossierEntity> dossierSet = new HashSet<>();

        for (DecisionEntity decision : decisions) {
            if (decision.getDossier() != null) {
                dossierSet.add(decision.getDossier());
            }
        }

        return dossierSet.stream().collect(Collectors.toList());
    }
}
