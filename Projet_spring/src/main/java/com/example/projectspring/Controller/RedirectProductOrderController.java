package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/redirect-product-order")
public class RedirectProductOrderController {

    private final ProduitService produitService;

    public RedirectProductOrderController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @PostMapping
    public RedirectView handlePostRequest(@RequestParam("produit_id") int produitId,
                                          @RequestParam(value = "commande_id", required = false) Integer commandeId) {
        if (produitService.getProduitById(produitId) != null) {
            commandeId = (commandeId == null) ? -1 : commandeId;
            return new RedirectView("/product-details?type=pageProduit&produit_id=" + produitId + "&commande_id=" + commandeId);
        } else {
            return new RedirectView("/WEB-INF/Vue/produitSupprime.jsp");
        }
    }

    @GetMapping
    public String handleGetRequest() {
        // Gérer les requêtes GET si nécessaire
        return "page_d'accueil"; // Remplacez par la page d'accueil ou une autre page par défaut
    }
}
