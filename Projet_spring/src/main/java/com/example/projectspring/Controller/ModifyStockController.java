package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/modify-stock-servlet")
public class ModifyStockController {

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public String modifyStock(@RequestParam("produit_id") Integer produitId, @RequestParam("stock") int newStock) {
        try {
            Produit produit = produitService.getProduitById(produitId);

            if (produit != null) {
                produit.setStock(newStock);
                produitService.updateProduct(produit);

                // Redirection vers la page de gestion des produits
                return "redirect:/product-details?produit_id="+produitId+"&type=+pageProduitVendeur"; // Assurez-vous d'avoir un mapping approprié pour la page de gestion des produits
            }
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            // Rediriger vers la page d'erreur en cas d'erreur
            return "error"; // Page d'erreur
        }

        return "redirect:/error"; // Redirection vers la page d'erreur par défaut
    }
}
