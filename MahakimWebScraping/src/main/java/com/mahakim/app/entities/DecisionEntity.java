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

/**
 * @author Ayoub
 *
 */

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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Decision_key", updatable = false)
	private int idDecisionKey;
	
	@Column(name = "id_Decision", updatable = false)
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
	
	@Column(name = "dateTime_Next_Audience")
	private Date dateTimeNextAudience;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_dossier_decision", joinColumns = @JoinColumn(name = "fk_id_Decision_key", referencedColumnName = "id_Decision_key"), inverseJoinColumns = @JoinColumn(name = "fk_id_Dossier_Civil", referencedColumnName = "id_Dossier_Civil"))
//	@JsonBackReference
//	@JsonProperty(access = Access.WRITE_ONLY)
	private DossierEntity dossier;
}