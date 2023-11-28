package com.example.projectspring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.projectspring.Service.ComptebancaireService;

@Controller
public class VerifyBankController {

    private ComptebancaireService comptebancaireService;

    public VerifyBankController(ComptebancaireService comptebancaireService) {
        this.comptebancaireService = comptebancaireService;
    }

    @GetMapping("/VerifyBankServlet")
    public String showVerifyBankPage() {
        return "verifyBank"; // Nom du fichier JSP ou HTML pour la vérification bancaire
    }

    @PostMapping("/VerifyBankServlet")
    public ModelAndView authenticateBank(@RequestParam("numero") String numero,
                                         @RequestParam("cvv") String cvv,
                                         @RequestParam("date") String date,
                                         @RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if (comptebancaireService.validate(numero, cvv, date, Integer.parseInt(id))) {
                modelAndView.setViewName("redirect:/CreateCommandeServlet"); // Redirection vers la page de création de commande
            } else {
                throw new Exception("Payment not successful.");
            }
        } catch (Exception e) {
            modelAndView.setViewName("redirect:/error");
            // Vous pouvez aussi ajouter des attributs au modèle pour afficher des messages d'erreur.
            // modelAndView.addObject("errorMessage", "Erreur de paiement");
        }
        return modelAndView;
    }
}
