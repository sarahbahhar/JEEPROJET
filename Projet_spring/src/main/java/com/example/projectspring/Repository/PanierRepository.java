package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Produitpanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanierRepository extends JpaRepository<Panier, String> {
    @Query("SELECT pp FROM Panier p JOIN p.produit pp WHERE p.email = :email") // le produit n'est pas bon
    List<Produitpanier> findProduitsByEmail(@Param("email") String email);
}

