package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Compte;

@Repository

public interface CompteRepository extends JpaRepository<Compte, String> {

/*
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO Compte (email, mot_de_passe) VALUES (:email, :motDePasse)", nativeQuery = true)

    void addCompte(@Param("email") String email, @Param("motDePasse") String motDePasse);

*/


}