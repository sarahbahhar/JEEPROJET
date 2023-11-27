package com.example.projectspring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.projectspring.Entity.Commentaires;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CommentairesRepository extends JpaRepository<Commentaires, Integer> {

    @Query("SELECT c FROM Commentaires c WHERE c.idProduit = :produitId")
    List<Commentaires> getCommentairesByProduitId(@Param("produitId") Integer produitId);

    @Query("SELECT c.idProduit, AVG(c.note) AS moyenne FROM Commentaires c GROUP BY c.idProduit ORDER BY moyenne ASC")
    List<Integer> getBestNotedIdProduct();

    @Query("SELECT COUNT(c) > 0 FROM Commentaires c WHERE c.email = :email AND c.idProduit = :produitId")
    boolean existsByEmailAndIdProduit(@Param("email") String email, @Param("produitId") Integer produitId);

    @Query("SELECT AVG(c.note) FROM Commentaires c WHERE c.idProduit = :produitId")
    BigDecimal getNoteMoyenneProduit(@Param("produitId") Integer produitId);

}

