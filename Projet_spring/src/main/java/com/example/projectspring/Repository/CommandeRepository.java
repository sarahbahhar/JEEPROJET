package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Commande;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {
    List<Commande> findByEmail(String email);

    @Query("SELECT DISTINCT C FROM Commande C WHERE C.numero IN " +
            "(SELECT PC.commandeNumero FROM Produitcommande PC WHERE PC.emailVendeur = :email)")
    List<Commande> findDistinctByProduitcommande_EmailVendeur(@Param("email") String email);

    Commande findTopByEmailOrderByNumeroDesc(String email);

    @Query("SELECT c FROM Commande c WHERE c.email = :email")
    List<Commande> findAllByEmail(@Param("email") String email);
}




