package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
public class MyProductListController {

    @Autowired
    private ProduitService produitService;

    @GetMapping("/my-product-list-servlet") // Même chemin d'accès que votre servlet
    public String myProductList(@RequestParam("email") String email, Model model) {
        try {
            List<Produit> listProduit = produitService.getListProduitByEmail(email);

            // Ajouter la liste de produits au modèle
            model.addAttribute("produits", listProduit);

            return "gererProduit"; // Assurez-vous que "gererProduit" est le nom de la vue appropriée
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            // Rediriger vers la page d'erreur en cas d'erreur
            return "error"; // Page d'erreur
        }
    }
}
