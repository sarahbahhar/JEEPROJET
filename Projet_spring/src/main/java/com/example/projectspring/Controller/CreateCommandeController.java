package com.example.projectspring.Controller;

import com.example.projectspring.Service.CommandeService;
import com.example.projectspring.Entity.Commande;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Service.InfoAccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/CreateCommandeServlet")
public class CreateCommandeController {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private InfoAccountService infocompteService;

    @GetMapping
    public String createCommande(HttpSession session, Model model) {
        try {
            Infocompte infocompte = (Infocompte) session.getAttribute("InfoCompte");
            String email = infocompte.getEmail();
            double totalDouble = (double) session.getAttribute("total");
            BigDecimal totalBigDecimal = new BigDecimal(totalDouble);
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            Commande commande = new Commande();
            commande.setEmail(email);
            commande.setTotal(totalBigDecimal);
            commande.setDateDePaiement(sqlDate);

            commandeService.addCommande(commande);
            int nCommande = commandeService.getLastCommandeIdByEmail(email);

            model.addAttribute("nCommande", nCommande);

            return "confirmationCommande"; // Nom de la vue confirmationCommande.jsp
        } catch (Exception e) {
            return "error";
        }
    }
}
