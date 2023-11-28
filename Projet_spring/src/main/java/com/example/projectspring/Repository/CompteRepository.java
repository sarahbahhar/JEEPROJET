package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Compte;

import java.util.Optional;

@Repository

public interface CompteRepository extends JpaRepository<Compte, String> {

/*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Compte (email, mot_de_passe) VALUES (:email, :motDePasse)", nativeQuery = true)

    void addCompte(@Param("email") String email, @Param("motDePasse") String motDePasse);

*/

    Optional<Compte> findByEmail(String email);

    @Query(value = "SELECT COUNT(*) FROM Compte C  WHERE C.email = :email", nativeQuery = true)
    long isUniqueEmail(@Param("email") String email);




}