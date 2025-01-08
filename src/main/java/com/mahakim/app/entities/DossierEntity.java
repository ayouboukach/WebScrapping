package com.mahakim.app.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_DOSSIER")
public class DossierEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8077449953901463124L;
	@Id
	@Column(name = "id_Dossier_Civil", updatable = false)
	private int idDossierCivil;

	@Builder.Default
	@Column(name = "id_Dossier_TF")
	private int idDossierTF = 0;

	@Column(name = "lib_Entite")
	private String libEntite;

	@Column(name = "type_Dossier")
	private String typeDossier;

	@Column(name = "juge_Rapporteur")
	private String jugeRapporteur;

	@Column(name = "date_Enregistrement_Dossier_Dans_Registre")
	private String dateEnregistrementDossierDansRegistre;

	@Column(name = "type_Requette")
	private String typeRequette;

	@NotNull
	@Column(name = "numero_Complet_National_Dossier_1Instance", nullable = false, unique = true)
	private String numeroCompletNationalDossier1Instance;

	@NotNull
	@Column(name = "numero_Complet_Dossier_1Instance", nullable = false)
	private String numeroCompletDossier1Instance;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_juridiction1Instance_dossier", joinColumns = @JoinColumn(name = "fk_id_Dossier_Civil", referencedColumnName = "id_Dossier_Civil"), inverseJoinColumns = @JoinColumn(name = "FK_id_juridiction_1Instance", referencedColumnName = "id_Juridiction"))
	private JuridictionEntity juridiction1Instance;

	@Column(name = "numero_Complet_National_Dossier_2Instance")
	private String numeroCompletNationalDossier2Instance;

	@Column(name = "numero_Complet_Dossier_2Instance")
	private String numeroCompletDossier2Instance;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_juridiction2Instance_dossier", joinColumns = @JoinColumn(name = "fk_id_Dossier_Civil", referencedColumnName = "id_Dossier_Civil"), inverseJoinColumns = @JoinColumn(name = "FK_id_juridiction_2Instance", referencedColumnName = "id_Juridiction"))
	private JuridictionEntity juridiction2Instance;

	@Column(name = "objet_Dossier")
	private String objetDossier;

	@Column(name = "libelle_Dernier_Jugement")
	private String libelleDernierJugement;

	@Builder.Default
	@Column(name = "etat_Dernier_Jugement")
	private String etatDernierJugement = "";

	@Column(name = "date_Etat_Jugement_Pret")
	private Date dateEtatJugementPret;

	@Column(name = "date_Dernier_Jugement")
	private Date dateDernierJugement;

	@Column(name = "id_Decision_Dernier_Jugement")
	private int idDecisionDernierJugement;

	@Column(name = "affaire", length = 3)
	private String affaire;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossier", cascade = CascadeType.ALL)
	List<DecisionEntity> decisions;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dossier", cascade = CascadeType.MERGE)
	Collection<PartieEntity> parties;
	
	@Transient
	private String juridiction1InstanceName;

	@Transient
	private String juridiction2InstanceName;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DossierEntity other = (DossierEntity) obj;
		if (idDossierCivil != other.idDossierCivil)
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idDossierCivil;
		return result;
	}
}
