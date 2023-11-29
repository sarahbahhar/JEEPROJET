package com.example.projectspring.Controller;


import com.example.projectspring.Entity.Compte;
import com.example.projectspring.Service.CompteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


@Controller
@RequestMapping("/inscriptionServlet")
@SessionAttributes({"email", "nom", "prenom", "password"})


public class InscriptionController {/*
    @PostMapping
    public String doPost(@RequestParam("action") String action,
                         @RequestParam(value = "productId", required = false) Integer productId,
                         @RequestParam(value = "imgFile", required = false) MultipartFile[] imgFileArray,
                         @RequestParam(value = "img", required = false) String[] imgString,
                         @RequestParam(value = "name", required = false) String[] nameString,
                         @RequestParam(value = "price", required = false) String[] priceString,
                         @RequestParam(value = "stock", required = false) String[] stockString,
                         Model model) {
        @GetMapping
        public String doGet(Model model, SessionStatus sessionStatus) {
            // Get the session and remove the user attribute to log them out
            model.addAttribute("user", null);
            sessionStatus.setComplete();
            return "redirect:/Index";
        }*/

    @Autowired
    private CompteService cs;






    @PostMapping

    public String doPost(@RequestParam("email") String email,
                         @RequestParam("password") String password,
                         @RequestParam("nom") String nom,
                         @RequestParam("prenom") String prenom, Model model,HttpSession session){
        try{

            Compte compte = new Compte();
            compte.setEmail(email);
            compte.setAndHashMotDePasse(password);


            cs.addCompte(compte);

            session.setAttribute("email", email);
            session.setAttribute("nom", nom);
            session.setAttribute("prenom", prenom);
            session.setAttribute("password", password);

            boolean test = cs.isUniqueEmail(email);
            model.addAttribute("isUnique",test);

            return "infoCompte";// ?


        }catch (Exception e) {
            e.printStackTrace();
            return "error";

    }


    }

}

