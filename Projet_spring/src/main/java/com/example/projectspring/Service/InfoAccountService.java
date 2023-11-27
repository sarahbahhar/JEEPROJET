package com.example.projectspring.Service;


import com.example.projectspring.Entity.Client;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.InfoAccountRepository;
import com.example.projectspring.Entity.Infocompte;

import java.util.List;
import java.util.Optional;

@Service

public class InfoAccountService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private InfoAccountRepository ir;




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


    public String[] getFullNameByEmail(String email) {
        String[] fullName = new String[2]; // Un tableau pour stocker le nom et le prénom

        try {
            Infocompte infoAccount = ir.findByEmail(email);
            if (infoAccount != null) {
                fullName[0] = infoAccount.getPrenom();
                fullName[1] = infoAccount.getNom();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullName;
    }

    public void addCompte(Infocompte infocompte) {
        try{
            ir.save(infocompte);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Infocompte infocompte(String email){
        Infocompte ic= null;
        try {
            ic = ir.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ic;
    }

    public void updateInfoCompte(String email, Infocompte updated) {
        try {
            // Charger l'Infocompte existant à partir de la base de données
            Infocompte toChange = ir.findByEmail(email);
            // Mettre à jour les champs de l'Infocompte existant avec les nouvelles valeurs
            toChange.setPrenom(updated.getPrenom());
            toChange.setNom(updated.getNom());
            toChange.setDateNaissance(updated.getDateNaissance());
            toChange.setTelephone(updated.getTelephone());
            toChange.setAdresse(updated.getAdresse());
            toChange.setVille(updated.getVille());
            toChange.setCodePostal(updated.getCodePostal());
            toChange.setPays(updated.getPays());

            // Enregistrer les modifications dans la base de données
            ir.save(toChange); //a verifier
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}




