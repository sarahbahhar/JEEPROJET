package com.example.projectspring.Service;

import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Repository.PanierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    public void createPanier(String email) {
        Panier panier = new Panier();
        panier.setEmail(email);
        panier.setHt(BigDecimal.ZERO);
        panier.setTva(BigDecimal.ZERO);
        panier.setTtc(BigDecimal.ZERO);
        panierRepository.save(panier);
    }

    public void updatePanier(String email, Panier updated) {
        Panier existingPanier = panierRepository.findById(email).orElse(null);
        if (existingPanier != null) {
            existingPanier.setHt(updated.getHt());
            existingPanier.setTva(updated.getTva());
            existingPanier.setTtc(updated.getTtc());

            panierRepository.save(existingPanier);
        }
    }

    public List<Produitpanier> getListProduitpanier(String email) {
        return panierRepository.findProduitsByEmail(email);
    }

    public void ajouterProduitAuPanier(String email, int produitId, int quantite) {
        // Logique pour ajouter un produit au panier en utilisant produitId et quantite
        // Assurez-vous de mettre à jour le panier et de sauvegarder les modifications
    }

    public void resetPanier(String email) {
        Panier panier = panierRepository.findById(email).orElse(null);
        if (panier != null) {
            panier.setHt(BigDecimal.ZERO);
            panier.setTva(BigDecimal.ZERO);
            panier.setTtc(BigDecimal.ZERO);
            panier.getProduits().clear(); // changer le getProduits

            panierRepository.save(panier);
        }
    }

    public void removeProduitPanier(String email) {
        // Logique pour supprimer tous les produits du panier pour l'email donné
        // Assurez-vous de mettre à jour le panier et de sauvegarder les modifications
    }

    public void removeProduitPanierById(String email, int produitId) {

    }

    public void changeQuantityById(String email, int produitId, int newQuantity) {

    }


}

