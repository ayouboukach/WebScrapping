package com.mahakim.app.mapper.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import org.springframework.stereotype.Component;

import com.mahakim.app.entities.DossierEntity;
import com.mahakim.app.entities.DossierEntity.DossierEntityBuilder;
import com.mahakim.app.mapper.DecisionMapper;
import com.mahakim.app.mapper.DossierMapper;
import com.mahakim.app.mapper.PartieMapper;
import com.mahakim.app.request.model.DecisionRequestBody;
import com.mahakim.app.request.model.DossierRequestBody;
import com.mahakim.app.request.model.DossierRequestBody.DossierRequestBodyBuilder;
import com.mahakim.app.request.model.PartieRequestBody;

import lombok.RequiredArgsConstructor;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T07:17:27+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_311 (Oracle Corporation)"
)
@Component
@RequiredArgsConstructor
public class DossierMapperImpl implements DossierMapper {

	private final DecisionMapper decisionMapper;
	private final PartieMapper partieMapper;
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	
    @Override
    public DossierEntity bodyToEntity(DossierRequestBody body) throws ParseException {
        if ( body == null ) {
            return null;
        }

        DossierEntityBuilder dossierEntity = DossierEntity.builder();

        dossierEntity.idDossierCivil( body.getIdDossierCivil() );
        dossierEntity.idDossierTF( body.getIdDossierTF() );
        dossierEntity.libEntite( body.getLibEntite() );
        dossierEntity.typeDossier( body.getTypeDossier() );
        dossierEntity.jugeRapporteur( body.getJugeRapporteur() );
        dossierEntity.dateEnregistrementDossierDansRegistre( body.getDateEnregistrementDossierDansRegistre() );
        dossierEntity.typeRequette( body.getTypeRequette() );
        dossierEntity.numeroCompletNationalDossier1Instance( body.getNumeroCompletNationalDossier1Instance() );
        dossierEntity.numeroCompletDossier1Instance( body.getNumeroCompletDossier1Instance() );
        dossierEntity.numeroCompletNationalDossier2Instance( body.getNumeroCompletNationalDossier2Instance() );
        dossierEntity.numeroCompletDossier2Instance( body.getNumeroCompletDossier2Instance() );
        dossierEntity.juridiction1InstanceName(body.getJuridiction1Instance());
        dossierEntity.juridiction2InstanceName(body.getJuridiction2Instance());
        dossierEntity.objetDossier( body.getObjetDossier() );
        dossierEntity.libelleDernierJugement( body.getLibelleDernierJugement() );
        dossierEntity.etatDernierJugement( body.getEtatDernierJugement() );
        if ( body.getDateEtatJugementPret()!=null &&  !body.getDateEtatJugementPret().isEmpty()) {
        	dossierEntity.dateEtatJugementPret(formatter.parse(body.getDateEtatJugementPret()));
		}
        dossierEntity.dateEtatJugementPret(null);
        if ( body.getDateDernierJugement()!=null &&  !body.getDateDernierJugement().isEmpty()) {
        	dossierEntity.dateDernierJugement(formatter.parse(body.getDateDernierJugement()));
		}
        dossierEntity.idDecisionDernierJugement( body.getIdDecisionDernierJugement() );
        dossierEntity.affaire( body.getAffaire() );
        dossierEntity.decisions(decisionMapper.listBodyToEntity((List<DecisionRequestBody>) body.getDecisions()));
        dossierEntity.parties(partieMapper.listBodyToEntity((List<PartieRequestBody>) body.getParties()));
        return dossierEntity.build();
    }

    @Override
    public DossierRequestBody entityToBody(DossierEntity entity) {
        if ( entity == null ) {
            return null;
        }

        DossierRequestBodyBuilder dossierRequestBody = DossierRequestBody.builder();

        dossierRequestBody.idDossierCivil( entity.getIdDossierCivil() );
        dossierRequestBody.idDossierTF( entity.getIdDossierTF() );
        dossierRequestBody.libEntite( entity.getLibEntite() );
        dossierRequestBody.typeDossier( entity.getTypeDossier() );
        dossierRequestBody.jugeRapporteur( entity.getJugeRapporteur() );
        dossierRequestBody.dateEnregistrementDossierDansRegistre( entity.getDateEnregistrementDossierDansRegistre() );
        dossierRequestBody.typeRequette( entity.getTypeRequette() );
        dossierRequestBody.numeroCompletNationalDossier1Instance( entity.getNumeroCompletNationalDossier1Instance() );
        dossierRequestBody.numeroCompletDossier1Instance( entity.getNumeroCompletDossier1Instance() );
        dossierRequestBody.juridiction1Instance( entity.getJuridiction1Instance().getNomJuridiction() );
        dossierRequestBody.numeroCompletNationalDossier2Instance( entity.getNumeroCompletNationalDossier2Instance() );
        dossierRequestBody.numeroCompletDossier2Instance( entity.getNumeroCompletDossier2Instance() );
        dossierRequestBody.juridiction2Instance( entity.getJuridiction2Instance().getNomJuridiction() );
        dossierRequestBody.objetDossier( entity.getObjetDossier() );
        dossierRequestBody.libelleDernierJugement( entity.getLibelleDernierJugement() );
        dossierRequestBody.etatDernierJugement( entity.getEtatDernierJugement() );
        dossierRequestBody.dateEtatJugementPret(formatter.format(entity.getDateEtatJugementPret() ));
        dossierRequestBody.dateDernierJugement(formatter.format(entity.getDateEtatJugementPret() ));
        dossierRequestBody.idDecisionDernierJugement( entity.getIdDecisionDernierJugement() );
        dossierRequestBody.affaire( entity.getAffaire() );

        return dossierRequestBody.build();
    }

    @Override
    public List<DossierEntity> listBodyToEntity(List<DossierRequestBody> bodyList) throws ParseException {
        if ( bodyList == null ) {
            return null;
        }

        List<DossierEntity> list = new ArrayList<DossierEntity>( bodyList.size() );
        for ( DossierRequestBody dossierRequestBody : bodyList ) {
            list.add( bodyToEntity( dossierRequestBody ) );
        }

        return list;
    }

    @Override
    public List<DossierRequestBody> listEntityToBody(List<DossierEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<DossierRequestBody> list = new ArrayList<DossierRequestBody>( entityList.size() );
        for ( DossierEntity dossierEntity : entityList ) {
            list.add( entityToBody( dossierEntity ) );
        }

        return list;
    }
}
