package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/tissu-products")
public class TissuProductsController {

    private final ProduitService produitService;

    public TissuProductsController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public String getTissuProducts(Model model) {
        List<Produit> tissuProducts = produitService.getProductsByCategory("Tissu");
        model.addAttribute("tissuProducts", tissuProducts);

        return "tissu"; // Nom du fichier JSP ou HTML pour la vue "tissu"
    }
}
