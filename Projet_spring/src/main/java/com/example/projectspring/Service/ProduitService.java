package com.example.projectspring.Service;


import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Repository.ProduitRepository;
import com.example.projectspring.Repository.PanierRepository;
import com.example.projectspring.Repository.ModeratorRepository;
import com.example.projectspring.Repository.ProduitPanierRepository;
import com.example.projectspring.Service.ModeratorService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Moderateur;
import com.example.projectspring.Entity.Produitpanier;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class ProduitService {

    public static final String PATH_IMAGE = "/../../src/main/webapp/img/";


    @Autowired
    private ProduitRepository pr;
    @Autowired
    private PanierRepository par;
    @Autowired
    private PanierService pas;
    @Autowired
    private ModeratorService ms;
    @Autowired
    private ModeratorRepository mr;
    @Autowired
    private ProduitPanierRepository ppr;





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

        return pr.findByEmail(email)
                ;
    }



    public void removeProductByIdOfTable(int id) {
        ppr.deleteByProduitId(id);
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
        return pr.findByCategorie(categorie);
    }

    public boolean isModerateurBanni(Produit produit) {
        Moderateur moderateur = mr.findByEmail(produit.getEmail());

        // Vérifiez si le modérateur existe et s'il est banni
        return moderateur != null && moderateur.getDateBanni() != null;
    }

    public void removeProductById(String p,String fileName1,String fileName2,int id) {


        String absolutePath = p + PATH_IMAGE;



        File imageDir = new File(absolutePath);
        System.out.println(absolutePath);



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



        System.out.println(id);
        this.removeProductByIdOfTable(id);

    }
    @org.springframework.transaction.annotation.Transactional
    public void removeProductByModerator(String localisation, String email) {
        List<Produit> produits = getListProduitByEmail(email);
        for (Produit produit : produits) {
            pas.removeProduitPanierById(produit.getId());
            removeProductById(localisation, produit.getNomImage(), produit.getNomImage2(), produit.getId());
        }
    }

    @Transactional
    public void addFullProduct(String p ,Produit produit, MultipartFile image1, MultipartFile image2) throws IOException {
        // Ajoutez la logique pour définir le chemin et les noms de fichier des images
        String absolutePath = p + PATH_IMAGE;

        // Sauvegarde des images
        String fileName1 = saveFile(image1, absolutePath);
        String fileName2 = saveFile(image2, absolutePath);

        // Définir les noms des images dans l'objet produit
        produit.setNomImage(fileName1);
        produit.setNomImage2(fileName2);

        // Sauvegarde du produit dans la base de données
        pr.save(produit);
    }

    private String saveFile(MultipartFile file, String path) throws IOException {
        if (!file.isEmpty()) {
            String uniqueID = UUID.randomUUID().toString();
            String fileName = uniqueID + "_" + file.getOriginalFilename();

            File outputFile = new File(path, fileName);
            file.transferTo(outputFile);
            return fileName;
        }
        return null;
    }




}
