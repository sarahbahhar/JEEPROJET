package com.example.projectspring.Entity;


import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "infocompte" ,schema = "projet_jee")
public class Infocompte {

    @Id
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "prenom", nullable = false, length = 50)
    private String prenom;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "dateNaissance", nullable = false)
    private Date dateNaissance;

    @Column(name = "telephone", nullable = false, length = 10)
    private String telephone;

    @Column(name = "adresse", nullable = false, length = 250)
    private String adresse;

    @Column(name = "ville", nullable = false, length = 50)
    private String ville;

    @Column(name = "codePostal", nullable = false)
    private int codePostal;

    @Column(name = "pays", nullable = false, length = 50)
    private String pays;

    @ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private Compte compte;

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getVille() {
        return ville;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    public Compte getCompte() {
        return compte;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }
}
