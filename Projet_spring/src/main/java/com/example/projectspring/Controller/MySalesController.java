package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Commande;
import com.example.projectspring.Entity.Produitcommande;
import com.example.projectspring.Service.CommandeService;
import com.example.projectspring.Service.ProduitCommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/my-sales-servlet")
public class MySalesController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private ProduitCommandeService produitCommandeService;

    @PostMapping
    public String listSales(@Param("email") String email, Model model) {
        
        if (email != null) {
            List<Commande> salesList = commandeService.getListOrderByEmailSeller(email);
            List<Produitcommande> listProduitCommande = produitCommandeService.getListProduitCommande();

            model.addAttribute("commandes", salesList);
            model.addAttribute("produitcommandes", listProduitCommande);
            model.addAttribute("type", "pageMySales");

            return "mesCommandes";
        } else {
            return "redirect:/error";
        }
    }
}
