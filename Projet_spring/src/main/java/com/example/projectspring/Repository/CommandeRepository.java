package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Commande;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByEmail(String email);
    List<Commande> findByEmailVendeur(String email);
    Commande findTopByEmailOrderByNumeroDesc(String email);

    @Query("SELECT c FROM Commande c WHERE c.email = :email")
    List<Commande> findAllByEmail(@Param("email") String email);
}




