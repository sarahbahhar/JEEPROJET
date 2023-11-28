package com.example.projectspring.Controller;

import com.example.projectspring.Service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpSession;

import com.example.projectspring.Entity.Moderateur;
import com.example.projectspring.Entity.Infocompte;
import com.example.projectspring.Entity.Client;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private CompteService cos;
    @Autowired
    private InfoAccountService ias;
    @Autowired
    private AdminService as;
    @Autowired
    private ModeratorService ms;
    @Autowired
    private CustomerService cs;
    @Autowired
    private DemandeModerateurService dms;

    @GetMapping
    public String showLoginForm() {
        return "signIn2";
    }

    @PostMapping
    public String authenticate(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        boolean isBan = false;

        if (cos.validate(email, password)) {
            session.setAttribute("email", email);
            session.setAttribute("demandeModerateur", dms.isEmailInModeratorRequests(email));


            if (as.emailExists(email)) {
                session.setAttribute("role", 2); // 2 pour admin


            } else if (ms.emailExists(email)) {


                ms.unbanByEmail(email);
                session.setAttribute("role", 1); // 1 pour moderateur


                Moderateur m = ms.getModeratorByEmail(email);


                session.setAttribute("maxProductsPerLine", m.getMaxProduitsLigne());
                session.setAttribute("nbBannissement", m.getNbBannissement());
                session.setAttribute("motifCourtBannissement", m.getMotifCourtBannissement());
                session.setAttribute("motifLongBanissement", m.getMotifLongBanissement());
                session.setAttribute("dateBanni", m.getDateBanni());


                Client c = cs.getClient(email);


                session.setAttribute("pointFidelite", c.getPointsFidelite());


                    if (m.getDateBanni() != null) {
                        session.setAttribute("canAddProduct", false);
                        session.setAttribute("canDeleteProduct", false);
                        isBan = true;
                    } else {
                        session.setAttribute("canAddProduct", m.getPeutAjouterProduit() == 1);
                        session.setAttribute("canDeleteProduct", m.getPeutSupprimerProduit() == 1);
                    }

                   if (isBan) {
                        return "yourAreBan";
                    }


        } else if (cs.emailExists(email)) {
            session.setAttribute("role", 0); // 0 pour client
            Client c = cs.getClient(email);
            session.setAttribute("pointFidelite", c.getPointsFidelite());
        }




        Infocompte ic = ias.getInfoCompte(email);

        session.setAttribute("InfoCompte", ic);



        return "header";




        } else {
            model.addAttribute("failLogin", true);
            return "signIn2";
        }


    }
}


