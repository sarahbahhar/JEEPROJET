package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Produitcommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.ProduitpanierPK;
import com.example.projectspring.Entity.Produitpanier;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProduitPanierRepository extends JpaRepository<Produitpanier, ProduitpanierPK> {
    List<Produitpanier> findByEmail(String email);
    Optional<Produitpanier> findByEmailAndProduitId(String email, int produitId);
    Optional<Produitpanier> findByEmailAndProduitId(String email, Integer produitId);

    @Modifying
    @Query("DELETE FROM Produitpanier p WHERE p.email = :email")
    void deleteByUserEmail(@Param("email") String email);

    @Modifying
    @Query("DELETE FROM Produitpanier p WHERE p.produitId = :produitId")
    void deleteByProduitId(@Param("produitId") int produitId);




}
