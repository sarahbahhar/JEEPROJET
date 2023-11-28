package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Infocompte;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository

public interface InfoAccountRepository extends JpaRepository<Infocompte, String> {

/*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Compte (email, mot_de_passe) VALUES (:email, :motDePasse)", nativeQuery = true)

    void addCompte(@Param("email") String email, @Param("motDePasse") String motDePasse);

*/


    @Query(value="SELECT * FROM Infocompte  WHERE email = :email", nativeQuery=true)
    Infocompte findByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Infocompte SET prenom = :prenom, nom = :nom, dateNaissance = :dateNaissance, telephone = :telephone, adresse = :adresse, ville = :ville, codePostal = :codePostal, pays = :pays WHERE email = :email", nativeQuery = true)
    void updateInfoCompte(
            @Param("prenom") String prenom,
            @Param("nom") String nom,
            @Param("dateNaissance") Date dateNaissance,
            @Param("telephone") String telephone,
            @Param("adresse") String adresse,
            @Param("ville") String ville,
            @Param("codePostal") String codePostal,
            @Param("pays") String pays,
            @Param("email") String email
    );







}