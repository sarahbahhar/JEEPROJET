package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Moderateur;
import com.example.projectspring.Service.ModeratorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/redirection-form-edit-moderator-servlet")
public class RedirectionFormEditModeratorController {

    private final ModeratorService moderatorService;

    public RedirectionFormEditModeratorController(ModeratorService moderatorService) {
        this.moderatorService = moderatorService;
    }

    @PostMapping
    @GetMapping
    public String handleRequest(@RequestParam("email") String email, Model model) {
        try {
            Moderateur moderator = moderatorService.getModeratorByEmail(email);
            model.addAttribute("moderator", moderator);
            return "Vue/formEditModerateur"; // Assurez-vous que ce chemin correspond à votre structure JSP
        } catch (Exception e) {
            // Gérer l'exception et rediriger vers la page d'erreur si nécessaire
            return "redirect:/error";
        }
    }
}
