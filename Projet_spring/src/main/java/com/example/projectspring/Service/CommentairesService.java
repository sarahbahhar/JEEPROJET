package com.example.projectspring.Service;

import com.example.projectspring.Entity.Commentaires;
import com.example.projectspring.Repository.CommentairesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentairesService {

    @Autowired
    private CommentairesRepository cor;

    public void addCommentaire(Commentaires commentaire) {
        cor.save(commentaire);
    }

    public List<Commentaires> getCommentairesByProduitId(Integer produitId) {
        return cor.getCommentairesByProduitId(produitId);
    }

    public BigDecimal getNoteMoyenneProduit(Integer produitId) {
        return cor.getNoteMoyenneProduit(produitId);
    }

    public List<Integer> getBestNotedIdProduct() {
        return cor.getBestNotedIdProduct();
    }

    public boolean hasCommented(int produitId, String email) {
        return cor.existsByEmailAndIdProduit(email, produitId);
    }
}

