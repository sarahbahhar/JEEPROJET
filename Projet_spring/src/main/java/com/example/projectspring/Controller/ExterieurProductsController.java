package com.example.projectspring.Controller;
import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/exterieur-products")
public class ExterieurProductsController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String getExterieurProducts(@RequestParam(value = "category", defaultValue = "Exterieur") String category, Model model) {
        List<Produit> exterieurProducts = produitService.getProductsByCategory(category);
        model.addAttribute("exterieurProducts", exterieurProducts);
        return "exterieur"; // Le nom de la vue à afficher (doit correspondre au nom du fichier JSP ou HTML)
    }
}
