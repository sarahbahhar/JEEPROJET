package com.example.projectspring.Controller;

import com.example.projectspring.Service.*;
import com.example.projectspring.Entity.Compte;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Entity.Panier;
import com.example.projectspring.Entity.Client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/infoCompteServlet")
public class InfoCompteController {

    @Autowired
    private CompteService compteService;

    @Autowired
    private InfoAccountService infoAccountService;

    @Autowired
    private PanierService panierService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TokenService tokenService;
    @GetMapping
    public ModelAndView showInfoComptePage(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("infoCompte"); // Nom de la vue infoCompte.jsp
        String email = (String) session.getAttribute("email");
        modelAndView.addObject("email", email);
        return modelAndView;
    }

    @PostMapping
    public String processInfoCompteForm(@RequestParam("date") String dateString, @RequestParam("telephone") String telephone,
                                        @RequestParam("adresse") String adresse, @RequestParam("ville") String ville,
                                        @RequestParam("codePostal") Integer codePostal, @RequestParam("pays") String pays,
                                        HttpSession session) {
        try {
            String email = (String) session.getAttribute("email");
            String nom = (String) session.getAttribute("nom");
            String prenom = (String) session.getAttribute("prenom");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Création du compte
            Compte compte = new Compte();
            compte.setEmail(email);
            compte.setAndHashMotDePasse((String) session.getAttribute("password"));
            session.removeAttribute("password");
            compteService.addCompte(compte);

            // Ajout du token
            tokenService.addToken(email);

            // Création de l'Infocompte
            Infocompte infocompte = new Infocompte();
            infocompte.setEmail(email);
            infocompte.setDateNaissance(sqlDate);
            infocompte.setTelephone(telephone);
            infocompte.setAdresse(adresse);
            infocompte.setVille(ville);
            infocompte.setCodePostal(codePostal);
            infocompte.setPays(pays);
            infocompte.setNom(nom);
            infocompte.setPrenom(prenom);
            infoAccountService.addCompte(infocompte);

            // Création du panier
            Panier panier = new Panier();
            BigDecimal zero = new BigDecimal(0);
            panier.setHt(zero);
            panier.setTtc(zero);
            panier.setTva(zero);
            panier.setEmail(email);
            panierService.createPanier(panier);

            // Création du client
            Client client = new Client();
            client.setEmail(email);
            client.setPointsFidelite(0);
            customerService.addCustomer(client);

            // Session invalide
            session.invalidate();

            // Redirection vers la page d'accueil
            return "redirect:/home";
        } catch (ParseException e) {
            e.printStackTrace();
            return "redirect:/error";
        }
    }
}
