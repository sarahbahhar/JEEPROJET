package com.example.projectspring.Controller;

import com.example.projectspring.Service.ComptebancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/delete-bank-account-servlet")
public class DeleteBankAccountController {

    @Autowired
    private ComptebancaireService comptebancaireService;

    @PostMapping
    public String deleteBankAccount(@RequestParam("email") String email, @RequestParam("id") int id) {
        try {
            if (email != null && !email.isEmpty() && id >0) {
                comptebancaireService.deleteComptebancaire(id);
                return "redirect:/bank-account-servlet";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/error";
    }
}
