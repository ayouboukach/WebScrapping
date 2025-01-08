package com.mahakim.app.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mahakim.app.entities.DecisionEntity;
import com.mahakim.app.entities.DossierEntity;

public interface DecisionRepo extends JpaRepository<DecisionEntity , Integer> {

	List<DecisionEntity> findByDossier(DossierEntity dossier);
	
	List<DecisionEntity> findAllByDossierIn(List<DossierEntity> dossiers);
	
	@Query("SELECT d FROM DecisionEntity d WHERE " +
	           "FUNCTION('DATE', d.dateTimeNextAudience) = FUNCTION('DATE', CURRENT_DATE) " +
	           "AND d.typeDecision != 'حكم قطعي'")
	    List<DecisionEntity> findDecisionsForToday();
    
    @Query("SELECT td FROM DecisionEntity td " +
    	       "WHERE (td.dateTimeNextAudience < CURRENT_TIMESTAMP OR td.dateTimeNextAudience IS NULL)" +
    	       "AND td.typeDecision != ' حكم قطعي' " +
    	       "AND td.idDecision IN (" +
    	       "  SELECT tdLatest.idDecision FROM DecisionEntity tdLatest " +
    	       "  WHERE tdLatest.dateTimeDecision = (" +
    	       "    SELECT MAX(tdInner.dateTimeDecision) " +
    	       "    FROM DecisionEntity tdInner " +
    	       "    WHERE tdInner.dossier = tdLatest.dossier" +
    	       "  )" +
    	       ")")
    	List<DecisionEntity> findDecisionsWithPastDateAndExcludedType();

}
