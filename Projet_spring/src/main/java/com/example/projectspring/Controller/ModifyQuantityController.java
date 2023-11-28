package com.example.projectspring.Controller;

import com.example.projectspring.Service.PanierService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ModifyQuantityController {

    @Autowired
    private PanierService panierService;

    @PostMapping("/modify-quantity-servlet") // Même chemin d'accès que votre servlet
    public String modifyQuantity(@RequestParam("produit_id") Integer produit_id, @RequestParam("email") String email, @RequestParam("quantite") Integer newQuantity) {
        try {
            panierService.changeQuantityById(email, produit_id, newQuantity);

            // Rediriger vers la page du panier avec le paramètre email
            return "redirect:/panier-servlet?email=" + email;
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            // Rediriger vers la page d'erreur en cas d'erreur
            return "error"; // Page d'erreur
        }
    }
}
