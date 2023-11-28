package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VelourProductsController {

    private final ProduitService produitService;

    public VelourProductsController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/velour-products")
    public String getVelourProducts(Model model) {
        List<Produit> velourProducts = produitService.getProductsByCategory("Velour");
        model.addAttribute("velourProducts", velourProducts);

        return "velour"; // Nom du fichier JSP ou HTML pour la vue "velour"
    }
}
