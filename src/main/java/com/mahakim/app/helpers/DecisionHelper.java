package com.mahakim.app.helpers;

import com.mahakim.app.entities.DecisionEntity;

import java.util.List;

public class DecisionHelper {

    public static void mettreAJourClesPremiere(List<DecisionEntity> decisionsApi, List<DecisionEntity> decisionsDb) {
        decisionsApi.forEach(apiDecision ->
                decisionsDb.stream()
                        .filter(apiDecision::equals)
                        .findFirst()
                        .ifPresent(dbDecision -> apiDecision.setIdDecisionKey(dbDecision.getIdDecisionKey())));
    }
}
