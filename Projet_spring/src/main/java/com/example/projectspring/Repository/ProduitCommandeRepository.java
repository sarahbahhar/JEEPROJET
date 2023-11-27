package com.example.projectspring.Repository;

import com.example.projectspring.Entity.Produitcommande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Compte;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProduitCommandeRepository extends JpaRepository<Produitcommande, Integer> {

    List<Produitcommande> findByCommandeNumero(int idCommande);



}
