package com.example.projetspring.Servlet;

import Entity.Client;
import Entity.Infocompte;
import Entity.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginServlet {

    @GetMapping("/connexion")
    public String showLoginPage() {
        return "signIn2";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String motDePasse, Model model) {
        // Votre logique d'authentification ici
        boolean isAuthenticated = true;

        if (isAuthenticated) {
            model.addAttribute("email", email);
            return "redirect:/signIn2.jsp"; // Remplacez "/success" par votre chemin souhaité
        } else {
            model.addAttribute("failLogin", true);
            return "signIn2";
        }
    }
}
