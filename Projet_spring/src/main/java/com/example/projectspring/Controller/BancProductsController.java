package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/banc-products-controller")
public class BancProductsController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String getBancProducts(Model model) {
        List<Produit> bancProducts = produitService.getProductsByCategory("Banc");
        model.addAttribute("bancProducts", bancProducts);

        return "banc"; // Assurez-vous que "banc" est le nom de votre vue (à créer dans /src/main/resources/templates)
    }
}

