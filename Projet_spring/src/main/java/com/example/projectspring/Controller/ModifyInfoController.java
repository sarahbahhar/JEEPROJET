package com.example.projectspring.Controller;

import com.example.projectspring.Service.InfoAccountService;
import com.example.projectspring.Entity.Infocompte;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/ModifyInfoServlet")

public class ModifyInfoController {

    @Autowired
    private InfoAccountService infoAccountService;

    @GetMapping
    public String showModifyInfoForm() {
        return "myProfile"; // Nom du fichier JSP pour afficher le formulaire de modification d'informations
    }

    @PostMapping
    public String modifyInfo(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("date") String dateString, @RequestParam("telephone") String telephone, @RequestParam("adresse") String adresse, @RequestParam("ville") String ville, @RequestParam("codePostal") Integer codePostal, @RequestParam("pays") String pays, Model model, HttpSession session) {
        try {
            // Parse la date au format java.sql.Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


            Infocompte ic =(Infocompte) session.getAttribute("InfoCompte");
            String email= ic.getEmail();

            Infocompte updated = new Infocompte();
            updated.setEmail(email);
            updated.setDateNaissance(sqlDate);
            updated.setTelephone(telephone);
            updated.setAdresse(adresse);
            updated.setVille(ville);
            updated.setCodePostal(codePostal);
            updated.setPays(pays);
            updated.setNom(nom);
            updated.setPrenom(prenom);

            // Met à jour les informations du compte
            infoAccountService.updateInfoCompte(email, updated);

            // Met à jour l'attribut "InfoCompte" dans le modèle
            model.addAttribute("InfoCompte", updated);

            return "myProfile"; // Nom du fichier JSP pour afficher le profil mis à jour
        } catch (Exception e) {
            // Gérer les erreurs ici
            e.printStackTrace();
            return "error"; // Page d'erreur
        }
    }
}
