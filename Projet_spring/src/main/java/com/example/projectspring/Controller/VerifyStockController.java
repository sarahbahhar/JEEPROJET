package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Client;
import com.example.projectspring.Entity.Comptebancaire;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Service.ComptebancaireService;
import com.example.projectspring.Service.CustomerService;
import com.example.projectspring.Service.PanierService;
import com.example.projectspring.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/verify-stock")
public class VerifyStockController {

    private final ComptebancaireService ComptebancaireService;
    private final CustomerService CustomerService;
    private final PanierService PanierService;
    private final ProduitService ProduitService;

    @Autowired
    public VerifyStockController(ComptebancaireService ComptebancaireService,
                                 CustomerService CustomerService,
                                 PanierService PanierService,
                                 ProduitService ProduitService) {
        this.ComptebancaireService = ComptebancaireService;
        this.CustomerService = CustomerService;
        this.PanierService = PanierService;
        this.ProduitService = ProduitService;
    }

    @GetMapping("/check")
    public String verifyStock(Model Model, HttpSession session) {
        try {
            Infocompte Infocompte = (Infocompte) session.getAttribute("InfoCompte");
            String email = Infocompte.getEmail();
            List<Comptebancaire> CartesBancaires = ComptebancaireService.getListCBByEmail(email);
            Model.addAttribute("cartesBancaires", CartesBancaires);

            List<Produitpanier> ListProduitpanier = PanierService.getListProduitpanier(email);
            for (Produitpanier Produitpanier : ListProduitpanier) {
                Produit Produit = ProduitService.getProduitById(Produitpanier.getProduitId());
                if (Produit.getStock() - Produitpanier.getQuantite() < 0) {
                    return "redirect:/error";
                }
            }

            Client Client = CustomerService.getClient(email);
            int pointFidelite = Client.getPointsFidelite();
            session.setAttribute("pointFidelite", pointFidelite);
            if (pointFidelite >= 100 && (double) session.getAttribute("total") > 10) {
                Model.addAttribute("totalReduit", (double) session.getAttribute("total") - 10);
                return "fidelite";
            } else {
                return "payment";
            }

        } catch (Exception e) {
            return "redirect:/error";
        }
    }
}
