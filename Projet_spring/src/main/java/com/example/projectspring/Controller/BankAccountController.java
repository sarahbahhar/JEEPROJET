package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Comptebancaire;
import com.example.projectspring.Service.ComptebancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/bank-account-controller")
@SessionAttributes({"email"})
public class BankAccountController {

    @Autowired
    private ComptebancaireService comptebancaireService;

    @GetMapping
    public String getBankAccounts(HttpSession session, Model model) {
        String userEmail = (String) session.getAttribute("email");
        List<Comptebancaire> listCB = comptebancaireService.getListCBByEmail(userEmail);
        model.addAttribute("accounts", listCB);

        return "bankAccountList"; // Assurez-vous que "bankAccountList" est le nom de votre vue (à créer dans /src/main/resources/templates)
    }
}
