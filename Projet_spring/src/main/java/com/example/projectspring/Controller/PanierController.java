package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Service.CommentairesService;
import com.example.projectspring.Service.PanierService;
import com.example.projectspring.Service.ProduitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/panier-servlet")
public class PanierController {

    @Autowired
    private ProduitService produitService;

    @Autowired
    private PanierService panierService;

    public PanierController(ProduitService produitService, PanierService panierService) {
        this.produitService = produitService;
        this.panierService = panierService;
    }

    @GetMapping
    public String afficherPanier(@RequestParam String email, Model model, HttpSession session) {
        List<Produitpanier> panier = panierService.getListProduitpanier(email);
        List<Produit> produitsDansPanier = new ArrayList<>();

        double total = 0.0;

        for (Produitpanier produitpanier : panier) {
            Produit produit = produitService.getProduitById(produitpanier.getProduitId());

            if (produit != null) {
                total += produit.getPrix().doubleValue() * produitpanier.getQuantite();
                produitsDansPanier.add(produit);
            }
        }

        Panier p = new Panier();
        BigDecimal zero = BigDecimal.ZERO;
        BigDecimal ttc = new BigDecimal(total);
        p.setTtc(ttc);
        p.setTva(zero);
        p.setHt(zero);
        p.setEmail(email);
        panierService.updatePanier(email, p);

        model.addAttribute("panier", panier);
        model.addAttribute("total", total);
        model.addAttribute("produitsDansPanier", produitsDansPanier);
        session.setAttribute("total", total);

        return "panier";
    }

    @PostMapping
    public String ajouterAuPanier(@RequestParam(value="produit_id") int produitId, @RequestParam(value="quantite") int quantite, @RequestParam(value="email") String email, Model model) {
        System.out.println("hello");
        panierService.ajouterProduitAuPanier(email, produitId, quantite);
        System.out.println("hellolkjkljlkjl");
        return "redirect:/panier-servlet?email=" + email;
    }
}
