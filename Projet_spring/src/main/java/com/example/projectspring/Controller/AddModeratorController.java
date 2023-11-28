package com.example.projectspring.Controller;

import com.example.projectspring.Service.DemandeModerateurService;
import com.example.projectspring.Service.ModeratorService;
import com.example.projectspring.Entity.Moderateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/add-moderator-servlet")
public class AddModeratorController {

    @Autowired
    private DemandeModerateurService demandeModerateurService;

    @Autowired
    private ModeratorService moderateurService;

    @GetMapping
    public String showModeratorForm() {
        return "moderatorForm"; // Le nom de la vue (à créer dans /src/main/resources/templates)
    }

    @PostMapping
    public String addModerator(@RequestParam(value = "email", required = false) String email
                                , Model model) {
        try {
            if (email != null && !email.isEmpty()) {
                demandeModerateurService.removeDismissedModerator(email);
                Moderateur moderateur = new Moderateur();
                moderateur.setEmail(email);
                moderateur.setMaxProduitsLigne(10);
                moderateur.setPeutAjouterProduit((byte) 1);
                moderateur.setPeutSupprimerProduit((byte) 1);
                moderateurService.addModerateur(moderateur);
                return "redirect:/moderator-servlet"; // Redirige vers la page des modérateurs
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "error"; // Le nom de la vue d'erreur (à créer dans /src/main/resources/templates)
    }
}
