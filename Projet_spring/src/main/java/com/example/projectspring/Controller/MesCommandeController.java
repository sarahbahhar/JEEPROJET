package com.example.projectspring.Controller;

import com.example.projectspring.Service.CommandeService;
import com.example.projectspring.Entity.Commande;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/mes-commandes-servlet")
public class MesCommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public String showCommandes() {
        return "mesCommandes"; // Nom du fichier JSP pour afficher les commandes
    }

    @PostMapping
    public String listCommandes(@RequestParam("email") String email, Model model) {
        if (email != null && !email.isEmpty()) {
            List<Commande> listCommande = commandeService.getListProduitByEmail(email);
            model.addAttribute("commandes", listCommande);
            model.addAttribute("type", "pageMyOrders");
        }
        return "mesCommandes";
    }
}
