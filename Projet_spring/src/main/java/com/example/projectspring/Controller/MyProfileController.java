package com.example.projectspring.Controller;

import com.example.projectspring.Service.ModeratorService;
import com.example.projectspring.Entity.Infocompte;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;

@Controller
public class MyProfileController {

    @Autowired
    private ModeratorService moderatorService;

    @GetMapping("/my-profile-servlet") // Même chemin d'accès que votre servlet
    public String myProfile(HttpSession session, Model model) {
        try {
            Infocompte ic = (Infocompte) session.getAttribute("InfoCompte");
            String emailVendeur = ic.getEmail(); // Récupération de l'email depuis la session

            BigDecimal averageRating = moderatorService.getAverageRatingByEmail(emailVendeur);

            // Ajouter l'averageRating au modèle
            model.addAttribute("averageRating", averageRating);

            return "myProfile"; // Assurez-vous que "myProfile" est le nom de la vue appropriée
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            // Rediriger vers la page d'erreur en cas d'erreur
            return "error"; // Page d'erreur
        }
    }
}
