package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/research-product")
public class ResearchProductController {

    private final ProduitService produitService;

    public ResearchProductController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public String getProductsByTitle(@RequestParam("query") String titre, Model model) {
        List<Produit> listProduct = produitService.getListProductByTitre(titre);
        model.addAttribute("produits", listProduct);
        return "Vue/researchProductResult"; // Mettre à jour le chemin vers votre JSP si nécessaire
    }
}
