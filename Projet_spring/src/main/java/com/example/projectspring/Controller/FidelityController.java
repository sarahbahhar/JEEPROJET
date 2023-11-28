package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Client;
import com.example.projectspring.Service.ComptebancaireService;
import com.example.projectspring.Service.CustomerService;
import com.example.projectspring.Service.PanierService;
import com.example.projectspring.Entity.Comptebancaire;
import com.example.projectspring.Entity.Infocompte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/FidelityServlet")
public class FidelityController {

    @Autowired
    private ComptebancaireService comptebancaireService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PanierService panierService;

    @GetMapping
    public String showFidelityPage(HttpSession session, Model model) {
        try {
            Infocompte infocompte = (Infocompte) session.getAttribute("InfoCompte");
            String email = infocompte.getEmail();
            List<Comptebancaire> cartesBancaires = comptebancaireService.getListCBByEmail(email);
            model.addAttribute("cartesBancaires", cartesBancaires);
            return "payment"; // Vue de paiement
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }

    @PostMapping
    public String processFidelityPoints(@RequestParam(value = "usePoints", required = false) String usePoints, HttpSession session) {
        try {
            if (usePoints != null && usePoints.equals("true")) {
                double total = (double) session.getAttribute("total");
                session.setAttribute("total", total - 10);

                Infocompte infocompte = (Infocompte) session.getAttribute("InfoCompte");
                String email = infocompte.getEmail();
                Client c=new Client();

                c.setEmail(infocompte.getEmail());
                int pf=(int)session.getAttribute("pointFidelite");
                c.setPointsFidelite(pf-100);

                int pointsFidelite = (int) session.getAttribute("pointFidelite");
                customerService.updateClient(email, c);

                // Redirection vers la page de paiement
                return "redirect:/payment";
            } else {
                // Redirection vers la page de paiement
                return "redirect:/payment";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
