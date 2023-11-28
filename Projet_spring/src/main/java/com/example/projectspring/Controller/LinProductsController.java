package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/lin-products")
@Controller
public class LinProductsController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String showLinProductsPage(Model model) {
        List<Produit> linProducts = produitService.getProductsByCategory("Lin");
        model.addAttribute("linProducts", linProducts);
        return "lin"; // Nom de la vue lin.jsp
    }
}
