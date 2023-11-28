package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Comptebancaire;
import com.example.projectspring.Service.ComptebancaireService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/new-bank-account-servlet") // Chemin du servlet
public class NewBankAccountController {

    @Autowired
    private ComptebancaireService comptebancaireService;

    @PostMapping // Gère les requêtes POST vers /new-bank-account-servlet
    public String addNewBankAccount(
            @RequestParam("email") String email,
            @RequestParam("nom") String nom,
            @RequestParam("numero") String numero,
            @RequestParam("expiration") String expiration,
            @RequestParam("cvv") String cvv,
            Model model
    ) {
        try {
            if (email != null && nom != null && numero != null && expiration != null && cvv != null) {
                Comptebancaire cB = new Comptebancaire();
                cB.setEmail(email);
                cB.setNom(nom);
                cB.setDate(expiration);
                cB.setNumero(numero);
                cB.setCvv(cvv);

                comptebancaireService.addCompte(cB);

                return "redirect:/bank-account-servlet?email=" + email;
            }
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
        }
        // Rediriger vers la page d'erreur en cas d'erreur
        return "error"; // Page d'erreur
    }
}
