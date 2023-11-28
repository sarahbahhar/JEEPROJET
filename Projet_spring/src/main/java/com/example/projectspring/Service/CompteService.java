package com.example.projectspring.Service;


import com.mysql.cj.protocol.a.CompressedInputStream;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.CompteRepository;
import com.example.projectspring.Entity.Compte;

import java.util.Optional;

@Service

public class CompteService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private CompteRepository cr;




    /*


     Sets the dependencies for the service.*
     @param br  The BasketRepository to be injected.
     @param cs  The CustomerService to be injected.
     @param ccs The CreditCardService to be injected.
     @param ps  The ProductService to be injected.
     @param us  The UserService to be injected.
     */


    /*
    public void setDependencies(CompteRepository cr) {
        this.cr = cr;
    }*/


    /*
        this.cs = cs;
        this.ccs = ccs;
        this.ps = ps;
        this.us = us;*/



    public void addCompte(Compte compte) {
        cr.save(compte);

    }

    public boolean isUniqueEmail(String email){

        boolean unique=true;
        long nbEmail;
        try {
            nbEmail= cr.isUniqueEmail(email);
            unique=(nbEmail==1);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return unique;

    }
    public boolean validate(String email, String password) {
        return cr.findByEmail(email)
                .map(compte -> compte.isMotDePasseCorrect(password))
                .orElse(false);
    }

    @Transactional
    public void changePasswordByEmail(String email, String newPassword) {
        Optional<Compte> compteOptional = cr.findByEmail(email);

        if (compteOptional.isPresent()) {
            Compte compte = compteOptional.get();
            compte.setAndHashMotDePasse(newPassword);
            cr.save(compte);
        } else {
            // Gérer le cas où aucun compte n'est trouvé pour l'email fourni
            // Par exemple, en lançant une exception personnalisée ou en retournant une indication d'échec
            throw new RuntimeException("Aucun compte trouvé avec l'email : " + email);
            // Ou retourner une valeur spécifique, selon votre logique d'application
        }
    }




}




