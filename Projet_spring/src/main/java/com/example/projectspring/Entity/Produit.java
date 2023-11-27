package com.example.projectspring.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produit", schema = "projet_jee")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titre", length = 255)
    private String titre;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "stock")
    private int stock;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "nomImage", length = 250)
    private String nomImage;
    @Column(name = "nomImage2")
    private String nomImage2;

    @Column(name = "miniDescription", length = 200)
    private String miniDescription;

    @Column(name = "prix", precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(name = "categorie", length = 100, nullable = false)
    private String categorie;

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getNomImage2() {
        return nomImage2;
    }

    public void setNomImage2(String nomImage2) {
        this.nomImage2 = nomImage2;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
