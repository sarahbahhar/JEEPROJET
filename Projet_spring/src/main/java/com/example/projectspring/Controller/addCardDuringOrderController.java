package com.example.projectspring.Controller;

import com.example.projectspring.Entity.Comptebancaire;
import com.example.projectspring.Service.ComptebancaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;

@Controller
@RequestMapping("/add-card-during-servlet")
@SessionAttributes({"email"})
public class addCardDuringOrderController {

    @Autowired
    private ComptebancaireService bAccountService;

    @PostMapping
    public String doPost(@RequestParam(value = "nom", required = false) String name,
                         @RequestParam(value = "numero", required = false) String numero ,
                         @RequestParam(value = "expiration", required = false) String expiration,
                         @RequestParam(value = "cvv", required = false) String cvvS,
                         HttpSession session,
                         Model model) {

        String email = (String) session.getAttribute("email");

        try {
            if (email != null && name != null && expiration != null && cvvS != null && numero != null) {
                Comptebancaire cB = new Comptebancaire();
                cB.setEmail(email);
                cB.setNom(name);
                cB.setDate(expiration);
                cB.setNumero(numero);
                cB.setCvv(cvvS);
                bAccountService.addCompte(cB);
            }

            List<Comptebancaire> cartesBancaires = bAccountService.getListCBByEmail(email);
            model.addAttribute("cartesBancaires", cartesBancaires);

            return "payment"; // Le nom de la vue (vérifiez le dossier /src/main/resources/templates)
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
