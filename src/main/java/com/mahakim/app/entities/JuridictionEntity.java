package com.mahakim.app.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TBL_JURIDICTION")
public class JuridictionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7186299186469929800L;
	@Id
	@Column(name = "id_Juridiction", updatable = false)
	private int idJuridiction;

	@Column(name = "nom_Juridiction", nullable = false, unique = true)
	private String nomJuridiction;

	@ManyToOne
	@JoinColumn(name = "id_Juridiction_Parent")
	private JuridictionEntity juridictionParent;

	@OneToMany(mappedBy = "juridictionParent")
	private Collection<JuridictionEntity> childJuridictions;

	@Column(name = "type_Juridication")
	private String typeJuridication;

	@Column(name = "url_Juridiction")
	private String urlJuridiction;

	@Column(name = "code_Juridiction")
	private String codeJuridiction;

	@Column(name = "url_Saj_Extension")
	private String urlSajExtension;
}
