package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProduitService produitService;

    public ProductController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping
    public String showProducts(Model model) {
        List<Produit> listProduit = produitService.getListProduit();
        model.addAttribute("produits", listProduit);
        return "productList";
    }

    @PostMapping
    public String manageProducts(@RequestParam(value = "email", required = false) String email, Model model) {
        if (email != null) {
            List<Produit> listProduit = produitService.getListProduitByEmail(email);
            model.addAttribute("produits", listProduit);
            return "ererProduit";
        } else {
            // Redirection ou autre logique si email est null
            return "";
        }
    }
}
