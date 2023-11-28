package com.example.projectspring.Controller;

import com.example.projectspring.Service.CommentairesService;
import com.example.projectspring.Service.ProduitService;
import com.example.projectspring.Entity.Commentaires;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Entity.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/commentaire-servlet")
public class CommentaireController {

    @Autowired
    private CommentairesService commentairesService;

    @Autowired
    private ProduitService produitService;

    @PostMapping
    public String addCommentaire(@RequestParam("commentaire") String commentaire,
                                 @RequestParam("produit_id") int produitId,
                                 @RequestParam("rating") int rating,
                                 HttpSession session) {

        Produit produit = produitService.getProduitById(produitId);
        String emailVendeur = produit.getEmail();

        Infocompte infocompte = (Infocompte) session.getAttribute("InfoCompte");
        String emailAcheteur = infocompte.getEmail();

        Commentaires newComment = new Commentaires();
        newComment.setIdProduit(produitId);
        newComment.setNote(rating);
        newComment.setEmailVendeur(emailVendeur);
        newComment.setCommentaire(commentaire);
        newComment.setEmail(emailAcheteur);

        // Enregistrement dans la base de données
        commentairesService.addCommentaire(newComment);

        // Redirection vers la page produit ou un message de confirmation
        return "redirect:/product-details?type=pageProduit&produit_id=" + produitId;
    }
}
