package com.mahakim.app.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

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
@Table(name = "TBL_AVOCAT")
@Builder
public class AvocatEntity implements Serializable {

	private static final long serialVersionUID = -6437925865348437399L;

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_avocat")
//	private int idAvocat;

	@Column(name = "nom_avocat_barreau")
	private String nomAvocatBarreau;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_partie_avocat", joinColumns = @JoinColumn(name = "fk_nom_avocat_barreau"), inverseJoinColumns = @JoinColumn(name = "fk_id_partie"))
	@Builder.Default
	private Set<PartieEntity> parties = new HashSet<PartieEntity>();

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		AvocatEntity other = (AvocatEntity) obj;
		return nomAvocatBarreau != null && nomAvocatBarreau.equals(other.nomAvocatBarreau);
	}

	@Override
	public int hashCode() {
		return nomAvocatBarreau != null ? nomAvocatBarreau.hashCode() : 0;
	}
}
