package com.example.projectspring.Service;

import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Repository.PanierRepository;
import com.example.projectspring.Repository.ProduitRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.ProduitPanierRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PanierService {

    @Autowired
    private PanierRepository par;
    private ProduitPanierRepository ppr;
    private ProduitService ps;
    private ProduitRepository pr;

    public void createPanier(Panier p) {
        par.save(p);
    }

    @Transactional
    public void updatePanier(String email, Panier updated) {
        Panier toChange = par.findById(email)
                .orElseThrow(() -> new EntityNotFoundException("Panier non trouvé avec l'email : " + email));

        toChange.setHt(updated.getHt());
        toChange.setTva(updated.getTva());
        toChange.setTtc(updated.getTtc());

        par.save(toChange);
    }

    public List<Produitpanier> getListProduitpanier(String email) {
        return ppr.findByEmail(email);
    }



    @Transactional
    public void ajouterProduitAuPanier(String email, int produitId, int quantite) {
        Produit produit = pr.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID : " + produitId));

        if (produit.getStock() >= quantite) {
            Optional<Produitpanier> existingProduitPanier = ppr.findByEmailAndProduitId(email, produitId);

            if (!existingProduitPanier.isPresent()) {
                Produitpanier produitPanier = new Produitpanier();
                produitPanier.setEmail(email);
                produitPanier.setProduitId(produitId);
                produitPanier.setQuantite(quantite);
                ppr.save(produitPanier);

            } else {
                Produitpanier produitPanier = existingProduitPanier.get();
                produitPanier.setQuantite(quantite + produitPanier.getQuantite());
                ppr.save(produitPanier);
            }

            // Mise à jour du stock
            produit.setStock(produit.getStock() - quantite);
            pr.save(produit);
        }
    }
    public Produitpanier produitPanierExists(Integer produitId, String email) {
        return ppr.findByEmailAndProduitId(email, produitId)
                .orElse(null);
    }

    @Transactional
    public void resetPanier(String email) {
        Panier panier = par.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Panier non trouvé avec l'email : " + email));

        panier.setHt(BigDecimal.ZERO);
        panier.setTva(BigDecimal.ZERO);
        panier.setTtc(BigDecimal.ZERO);

        par.save(panier);
    }

    @Transactional
    public void removeProduitPanier(String email) {
        ppr.deleteByUserEmail(email);
    }
    @Transactional
    public void removeProduitPanierById(int id) {
        ppr.deleteByProduitId(id);
    }
    public void changeQuantityById(String email, int produitId, int newQuantity) {
        Produit produit = pr.findById(produitId)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID : " + produitId));

        Optional<Produitpanier> produitPanierOpt = ppr.findByEmailAndProduitId(email, produitId);

        produitPanierOpt.ifPresent(produitPanier -> {
            if (newQuantity == 0) {
                ppr.delete(produitPanier);
            } else if (produit.getStock() >= newQuantity) {
                produitPanier.setQuantite(newQuantity);
                ppr.save(produitPanier);
            }
        });
    }






}

