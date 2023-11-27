package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    List<Produit> findByEmail(String email);
    void deleteById(int produitId);
    List<Produit> findByTitreContaining(String titre);
    List<Produit> findByCategorie(String categorie);


}
