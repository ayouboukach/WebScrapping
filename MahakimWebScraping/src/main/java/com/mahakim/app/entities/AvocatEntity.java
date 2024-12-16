package com.mahakim.app.entities;

import java.io.Serializable;
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
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@Table(name = "TBL_AVOCAT")
//@IdClass( AvocatEntityId.class )
@Builder
@Data
@EqualsAndHashCode
public class AvocatEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6437925865348437399L;
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "id_avocat", updatable = false)
//	private int idAvocat;

//	@Id
	@Column(name = "nom_avocat_barreau",unique = true)
	private String nomAvocatBarreau;

	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_partie_avocat", joinColumns = @JoinColumn(name = "fk_nom_avocat_barreau"), inverseJoinColumns = @JoinColumn(name = "fk_id_partie"))
//	@JsonBackReference
//	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<PartieEntity> parties;
}