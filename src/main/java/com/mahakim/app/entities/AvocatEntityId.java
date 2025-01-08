package com.mahakim.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class AvocatEntityId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="OPTION_VALUE")
	@Column(name = "id_avocat")
	private int idAvocat;
	@Column(name = "nom_avocat_barreau")
	private String nomAvocatBarreau;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AvocatEntityId other = (AvocatEntityId) obj;
		if (idAvocat != other.idAvocat)
			return false;
		if (nomAvocatBarreau == null) {
			if (other.nomAvocatBarreau != null)
				return false;
		} else if (!nomAvocatBarreau.equals(other.nomAvocatBarreau))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idAvocat;
		result = prime * result + ((nomAvocatBarreau == null) ? 0 : nomAvocatBarreau.hashCode());
		return result;
	}
}
