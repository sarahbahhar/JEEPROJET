package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cuir-products")
public class CuirProductsController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String getCuirProducts(Model model) {
        try {
            List<Produit> cuirProducts = produitService.getProductsByCategory("Cuir");
            model.addAttribute("cuirProducts", cuirProducts);

            return "cuir"; // Vue cuir.jsp
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
