package com.mahakim.app.services.avocats;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mahakim.app.client.APIClientService;
import com.mahakim.app.entities.AvocatEntity;
import com.mahakim.app.entities.PartieEntity;
import com.mahakim.app.mapper.AvocatMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AvocatServiceImpl implements AvocatService {

    private final APIClientService apiClientService;
    private final AvocatMapper avocatMapper;

    @Override
    public List<PartieEntity> actualiserAvocatsPourParties(List<PartieEntity> parties) {
        return parties.stream()
                .peek(this::chercherEtAjouterAvocatsPourPartie)
                .collect(Collectors.toList());
    }

    private void chercherEtAjouterAvocatsPourPartie(PartieEntity partie) {
        Set<AvocatEntity> avocats = Optional.ofNullable(apiClientService
                .getAvocatsByIdPartie(String.valueOf(partie.getIdPartie()), partie.getCodeTypePersonne(), "DC"))
                .orElseGet(List::of)
                .stream()
                .map(avocatMapper::bodyToEntity)
                .collect(Collectors.toSet());
        if (!avocats.isEmpty()) {
            avocats.forEach(avocat -> avocat.setParties(Set.of(partie)));
            partie.setAvocats(avocats);
        }
    }
}