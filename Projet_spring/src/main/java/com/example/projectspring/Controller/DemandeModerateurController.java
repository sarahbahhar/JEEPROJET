package com.example.projectspring.Controller;

import com.example.projectspring.Service.DemandeModerateurService;
import com.example.projectspring.Entity.Demandemoderateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/demande-moderator-servlet")
public class DemandeModerateurController {

    @Autowired
    private DemandeModerateurService demandeModerateurService;

    @GetMapping
    public String showForm() {
        return "formAddModerator";
    }

    @PostMapping
    public String submitForm(
            @RequestParam("email") String email,
            @RequestParam("description") String description,
            HttpSession session,
            Model model
    ) {
        try {
            Demandemoderateur demandemoderateur = new Demandemoderateur();
            demandemoderateur.setEmail(email);
            demandemoderateur.setMessage(description);

            demandeModerateurService.addModerator(demandemoderateur);

            session.setAttribute("demandeModerateur", true);

            return "myProfile";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
