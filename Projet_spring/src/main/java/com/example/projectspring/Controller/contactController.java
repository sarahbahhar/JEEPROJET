package com.example.projectspring.Controller;

import com.example.projectspring.Service.AdminService;
import com.example.projectspring.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ContactController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contactForm"; // Nom de la vue contactForm.jsp
    }

    @PostMapping("/contact")
    public String processContactForm(@RequestParam("emailCompte") String emailCompte,
                                     @RequestParam("message") String message,
                                     Model model, HttpServletRequest request) {
        try {
            String emailAdmin = adminService.getAdminEmail();

            model.addAttribute("message", message);
            model.addAttribute("emailCompte", emailCompte);

            String htmlContent = emailService.getHtmlContentFromJsp("/mail/mailContact.jsp", request);

            emailService.sendEmailWithHTML(emailAdmin, "Contact", htmlContent);

            return "mailSendContact"; // Nom de la vue mailSendContact.jsp
        } catch (Exception e) {
            return "error";
        }
    }
}
