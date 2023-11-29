package com.example.projectspring.Controller;

import com.example.projectspring.Service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reset-password-servlet")
public class ResetPasswordController {

    @Autowired
    private final CompteService compteService;


    public ResetPasswordController(CompteService compteService) {
        this.compteService = compteService;
    }

    @PostMapping
    public String resetPassword(@RequestParam("email") String email,
                                @RequestParam("newPassword") String newPassword) {
        compteService.changePasswordByEmail(email, newPassword);

        // Redirection après la mise à jour du mot de passe
        return "redirect:/home"; // Modifier l'URL de redirection selon vos besoins
    }
}
