package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Service.CustomerService;
import com.example.projectspring.Service.PanierService;
import com.example.projectspring.Service.ProduitCommandeService;
import com.example.projectspring.Entity.Produit;
import com.example.projectspring.Entity.Produitcommande;
import com.example.projectspring.Entity.Produitpanier;
import com.example.projectspring.Service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/AddProductCommandeServlet")
@SessionAttributes({"InfoCompte"})
public class AddProductCommandeController {

    @Autowired
    private PanierService panierService;

    @Autowired
    private ProduitCommandeService produitCommandeService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String addProductCommande(HttpSession session,
                                     @RequestParam(value = "nCommande") int nCommande,

                                     Model model) {
        try {

            double total= (double) session.getAttribute("total");
            System.out.println(nCommande+ " ; "+ total);
            Infocompte ic = (Infocompte) session.getAttribute("InfoCompte");
            String email = ic.getEmail();
            BigDecimal totalBigDecimal = new BigDecimal(total);
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            List<Produitpanier> list = panierService.getListProduitpanier(email);

            for (Produitpanier pp : list) {
                Produit p = produitService.getProduitById(pp.getProduitId());

                p.setStock(p.getStock() - pp.getQuantite());
                produitService.updateProduct(p);

                Produitcommande pc = new Produitcommande();
                pc.setCommandeNumero(nCommande);
                pc.setQuantite(pp.getQuantite());
                pc.setEmailVendeur(p.getEmail());
                pc.setTitre(p.getTitre());
                pc.setPrix(p.getPrix());
                pc.setIdProduit(pp.getProduitId());
                produitCommandeService.insertProduitCommande(pc);
            }

            panierService.resetPanier(email);
            panierService.removeProduitPanier(email);
            customerService.addPointFidelite(email, (int) total);

            session.setAttribute("pointFidelite", customerService.getClient(email).getPointsFidelite());

            return "confirmationCommande"; // Le nom de la vue (à créer dans /src/main/resources/templates)

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", true);
            return "redirect:/error"; // Le nom de la vue d'erreur (à créer dans /src/main/resources/templates)
        }
    }
}
