package com.example.projectspring.Controller;

import com.example.projectspring.Service.ProduitCommandeService;
import com.example.projectspring.Entity.Produitcommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/commande-detail")
public class CommandeDetailController {

    @Autowired
    private ProduitCommandeService produitCommandeService;

    @GetMapping
    public String getCommandeDetail(@RequestParam("commande_id") int idCommande, Model model) {
        List<Produitcommande> listProduitCommande = produitCommandeService.getListProduitCommande(idCommande);
        model.addAttribute("produitcommandes", listProduitCommande);
        return "produitCommande";
    }
}
