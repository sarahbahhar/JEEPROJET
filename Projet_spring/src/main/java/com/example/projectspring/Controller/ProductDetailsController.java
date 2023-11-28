package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Commentaires;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Service.CommentairesService;
import com.example.projectspring.Service.ProduitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/product-details")
public class ProductDetailsController {

    private final ProduitService produitService;
    private final CommentairesService commentairesService;

    public ProductDetailsController(ProduitService produitService, CommentairesService commentairesService) {
        this.produitService = produitService;
        this.commentairesService = commentairesService;
    }

    @PostMapping
    @GetMapping
    public String showProductDetails(@RequestParam("produit_id") int produitId,
                                     @RequestParam(value = "commande_id", required = false) Integer commandeId,
                                     @RequestParam(value = "type", required = false) String type,
                                     @SessionAttribute(value = "InfoCompte", required = false) Infocompte ic,
                                     Model model) {
        BigDecimal noteMoyenne = commentairesService.getNoteMoyenneProduit(produitId);
        Produit produit = produitService.getProduitById(produitId);
        List<Commentaires> commentaires = commentairesService.getCommentairesByProduitId(produitId);

        model.addAttribute("produit", produit);
        model.addAttribute("noteMoyenne", noteMoyenne);
        model.addAttribute("commentaires", commentaires);
        model.addAttribute("commande_id", commandeId != null ? commandeId : -1);
        model.addAttribute("canComment", commandeId != null && commandeId > 0 && !commentairesService.hasCommented(produitId, ic != null ? ic.getEmail() : null));
        model.addAttribute("isModBanned", produitService.isModerateurBanni(produit));

        return "Vue/" + ("pageProduit".equals(type) ? "pageProduit" : "pageProduitVendeur");
    }
}
