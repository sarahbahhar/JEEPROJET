package com.example.projectspring.Entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "moderateur", schema = "projet_jee")
public class Moderateur {

    @Id
    private String email;

    @Column(name = "peut_ajouter_produit")
    private byte peutAjouterProduit;

    @Column(name = "peut_supprimer_produit")
    private byte peutSupprimerProduit;

    @Column(name = "max_produits_ligne")
    private int maxProduitsLigne;

    @Column(name = "nbBannissement")
    private int nbBannissement;

    @Column(name = "dateBanni", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateBanni;

    @Column(name = "motifCourtBannissement", length = 100, nullable = false)
    private String motifCourtBannissement;

    @Column(name = "motifLongBanissement", columnDefinition = "text", nullable = false)
    private String motifLongBanissement;

    // Getter and Setter methods

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getPeutAjouterProduit() {
        return peutAjouterProduit;
    }

    public void setPeutAjouterProduit(byte peutAjouterProduit) {
        this.peutAjouterProduit = peutAjouterProduit;
    }

    public byte getPeutSupprimerProduit() {
        return peutSupprimerProduit;
    }

    public void setPeutSupprimerProduit(byte peutSupprimerProduit) {
        this.peutSupprimerProduit = peutSupprimerProduit;
    }

    public int getMaxProduitsLigne() {
        return maxProduitsLigne;
    }

    public void setMaxProduitsLigne(int maxProduitsLigne) {
        this.maxProduitsLigne = maxProduitsLigne;
    }

    public int getNbBannissement() {
        return nbBannissement;
    }

    public void setNbBannissement(int nbBannissement) {
        this.nbBannissement = nbBannissement;
    }

    public Date getDateBanni() {
        return dateBanni;
    }

    public void setDateBanni(Date dateBanni) {
        this.dateBanni = dateBanni;
    }

    public String getMotifCourtBannissement() {
        return motifCourtBannissement;
    }

    public void setMotifCourtBannissement(String motifCourtBannissement) {
        this.motifCourtBannissement = motifCourtBannissement;
    }

    public String getMotifLongBanissement() {
        return motifLongBanissement;
    }

    public void setMotifLongBanissement(String motifLongBanissement) {
        this.motifLongBanissement = motifLongBanissement;
    }
}
