package com.example.projectspring.Service;


import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Repository.ProduitRepository;
import com.example.projectspring.Repository.PanierRepository;
import com.example.projectspring.Repository.ModeratorRepository;
import com.example.projectspring.Service.ModeratorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Moderateur;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ProduitService {
    public static final String PATH_IMAGE = "/src/main/webapp/img/";


    @Autowired
    private ProduitRepository pr;
    private PanierRepository par;
    private PanierService pas;
    private ModeratorService ms;
    private ModeratorRepository mr;



    public void addProduct(Produit p) {
        pr.save(p);
    }

    public List<Produit> getListProduit() {
        List<Produit> allProducts = pr.findAll();

        return allProducts.stream()
                .filter(p -> !this.isModerateurBanni(p))
                .collect(Collectors.toList());
    }
    public List<Produit> getListProduitByEmail(String email) {
        return pr.findByEmail(email);
    }


    @Transactional
    public void removeProductByIdOfTable(int id) {
        par.deleteByProduitId(id);
        pr.deleteById(id);
    }

    public List<Produit> getListProductByTitre(String titre) {
        List<Produit> allProducts = pr.findByTitreContaining(titre);

        return allProducts.stream()
                .filter(p -> !this.isModerateurBanni(p))
                .collect(Collectors.toList());
    }

    private ProduitRepository produitRepository;

    public Produit getProduitById(int produitId) {
        return pr.findById(produitId).orElse(null);
    }


    public void updateProduct(Produit produit) {
        pr.findById(produit.getId())
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'ID : " + produit.getId()));

        pr.save(produit);
    }

    public List<Produit> getProductsByCategory(String categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    public boolean isModerateurBanni(Produit produit) {
        Moderateur moderateur = mr.findByEmail(produit.getEmail());

        // Vérifiez si le modérateur existe et s'il est banni
        return moderateur != null && moderateur.getDateBanni() != null;
    }

    public void removeProductById(String p,String fileName1,String fileName2,int id) {

        String absolutePath = p+"../../" + PATH_IMAGE;

        File imageDir = new File(absolutePath);


        File file = new File(imageDir, fileName1);
        File file2 = new File(imageDir, fileName2);

        if (file.exists()&&file2.exists()) {
            if (file.delete()&&file2.delete()) {
                System.out.println("Image file deleted: " + fileName1+fileName2);

            } else {
                System.err.println("Failed to delete image file: " + fileName1+fileName2);
            }
        } else {
            System.err.println("Image file not found: " + fileName1+fileName2);
        }

        this.removeProductByIdOfTable(id);

    }
    public void removeProductByModerator(String localisation, String email) {
        List<Produit> produits = getListProduitByEmail(email);
        for (Produit produit : produits) {
            pas.removeProduitPanierById(produit.getId());
            removeProductById(localisation, produit.getNomImage(), produit.getNomImage2(), produit.getId());
        }
    }



}
