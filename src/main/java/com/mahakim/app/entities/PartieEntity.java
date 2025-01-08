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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "TBL_PARTIE")
@Builder
public class PartieEntity implements Serializable {

    private static final long serialVersionUID = -6437925865348437399L;

    @Id
    @Column(name = "id_Partie", nullable = false)
    private Integer idPartie;

    @Column(name = "code_Type_Personne", length = 2)
    private String codeTypePersonne;

    @Column(name = "role_Partie")
    private String rolePartie;

    @Column(name = "nom_Prenom_Partie")
    private String nomPrenomPartie;

    @Column(name = "count_Avocats_Partie")
    private int countAvocatsPartie;

    @Column(name = "count_Huissiers_Partie")
    private int countHuissiersPartie;

    @Column(name = "count_Mandataires_Partie")
    private int countMandatairesPartie;
    
    @Column(name = "count_Representants_Partie")
    private int countRepresentantsPartie;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
        name = "tbl_dossier_partie", 
        joinColumns = @JoinColumn(name = "fk_id_Partie", referencedColumnName = "id_Partie"), 
        inverseJoinColumns = @JoinColumn(name = "fk_id_Dossier_Civil", referencedColumnName = "id_Dossier_Civil")
    )
    private DossierEntity dossier;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "parties", cascade = CascadeType.ALL)  // Changed cascade type to MERGE
    private Set<AvocatEntity> avocats;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PartieEntity other = (PartieEntity) obj;
        return idPartie != null && idPartie.equals(other.idPartie);
    }

    @Override
    public int hashCode() {
        return idPartie != null ? idPartie.hashCode() : 0;
    }
}