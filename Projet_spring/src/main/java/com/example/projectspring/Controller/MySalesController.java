package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Commande;
import com.example.projectspring.Service.CommandeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MySalesController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping("/my-sales-servlet") // Même chemin d'accès que votre servlet
    public String mySales() {
        return "mySales"; // Assurez-vous que "mySales" est le nom de la vue appropriée
    }

    @PostMapping("/my-sales-servlet")
    public String listMySales(@RequestParam("email") String email, Model model) {
        try {
            List<Commande> salesList = commandeService.getListOrderByEmailSeller(email);

            // Ajouter la liste des ventes au modèle
            model.addAttribute("commandes", salesList); // recalculer le total
            model.addAttribute("type", "pageMySales");

            return "mesCommandes"; // Assurez-vous que "mesCommandes" est le nom de la vue appropriée
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            // Rediriger vers la page d'erreur en cas d'erreur
            return "error"; // Page d'erreur
        }
    }
}
