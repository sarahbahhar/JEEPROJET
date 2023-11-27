package com.example.projectspring.Service;


import com.example.projectspring.Repository.ComptebancaireRepository;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projectspring.Entity.Comptebancaire;

import java.util.List;
import java.util.Optional;

@Service

public class ComptebancaireService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private ComptebancaireRepository cbr;

    public void addCompte(Comptebancaire comptebancaire) {
        cbr.save(comptebancaire);

    }
    public void deleteComptebancaire(int id) {
        cbr.findById(id).ifPresent(cbr::delete);
    }

    public List<Comptebancaire> getListCBByEmail(String email) {
        return cbr.findByEmail(email);
    }

    public boolean validate(String numero, String cvv, String date, int id) {
        Optional<Comptebancaire> compteOpt = cbr.findById(id);

        if (compteOpt.isPresent()) {
            Comptebancaire compte = compteOpt.get();
            return compte.getDate().equals(date) && compte.getCvv().equals(cvv);
        }

        return false;
    }

}
