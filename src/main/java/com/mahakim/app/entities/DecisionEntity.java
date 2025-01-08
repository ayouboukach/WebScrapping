package com.mahakim.app.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity	
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TBL_DECISION")
@Builder
public class DecisionEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6437925865348437399L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_Decision_key")
    private int idDecisionKey;
	
	@Column(name = "id_Decision")
	private int idDecision;
	
	@Column(name = "date_De")
	private Date dateDe;
	
	@Column(name = "date_NA")
	private Date dateNA;
	
	@NotNull
	@Column(name = "date_Time_Decision", nullable = false)
	private Date dateTimeDecision;

	@NotNull
	@Column(name = "type_Decision", nullable = false)
	private String typeDecision;
	
	@Column(name = "contenu_Decision")
	@Lob
	private String contenuDecision;
	
	@Column(name = "numero_Jugement")
	private String numeroJugement;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTimeDecision == null) ? 0 : dateTimeDecision.hashCode());
		result = prime * result + idDecision;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DecisionEntity other = (DecisionEntity) obj;
		if (dateTimeDecision == null) {
			if (other.dateTimeDecision != null)
				return false;
		} else if (!dateTimeDecision.equals(other.dateTimeDecision))
			return false;
		if (idDecision != other.idDecision)
			return false;
		return true;
	}

	@Column(name = "dateTime_Next_Audience")
	private Date dateTimeNextAudience;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_dossier_decision", joinColumns = @JoinColumn(name = "fk_id_Decision_key", referencedColumnName = "id_Decision_key"), inverseJoinColumns = @JoinColumn(name = "fk_id_Dossier_Civil", referencedColumnName = "id_Dossier_Civil"))
	private DossierEntity dossier;
}