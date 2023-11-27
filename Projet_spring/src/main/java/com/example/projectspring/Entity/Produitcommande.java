package com.example.projectspring.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produitcommande")
public class Produitcommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_produit")
    private int idProduit;

    @Column(name = "commande_numero")
    private int commandeNumero;

    private int quantite;

    @Column(name = "email_vendeur")
    private String emailVendeur;

    private String titre;

    private BigDecimal prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getCommandeNumero() {
        return commandeNumero;
    }

    public void setCommandeNumero(int commandeNumero) {
        this.commandeNumero = commandeNumero;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getEmailVendeur() {
        return emailVendeur;
    }

    public void setEmailVendeur(String emailVendeur) {
        this.emailVendeur = emailVendeur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produitcommande that = (Produitcommande) o;

        if (id != that.id) return false;
        if (idProduit != that.idProduit) return false;
        if (commandeNumero != that.commandeNumero) return false;
        if (quantite != that.quantite) return false;
        if (emailVendeur != null ? !emailVendeur.equals(that.emailVendeur) : that.emailVendeur != null) return false;
        if (titre != null ? !titre.equals(that.titre) : that.titre != null) return false;
        return prix != null ? prix.equals(that.prix) : that.prix == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + idProduit;
        result = 31 * result + commandeNumero;
        result = 31 * result + quantite;
        result = 31 * result + (emailVendeur != null ? emailVendeur.hashCode() : 0);
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        return result;
    }
}
