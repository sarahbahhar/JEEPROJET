package com.example.projectspring.Service;

import com.example.projectspring.Entity.Commande;
import com.example.projectspring.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository cr;

    public List<Commande> getListProduitByEmail(String email) {
        return cr.findByEmail(email);
    }

    public List<Commande> getListOrderByEmailSeller(String email) {
        return cr.findDistinctByProduitcommande_EmailVendeur(email);
    }

    public void addCommande(Commande c) {
        cr.save(c);
    }

    public int getLastCommandeIdByEmail(String email) {
        Commande commande = cr.findTopByEmailOrderByNumeroDesc(email);
        return (commande != null) ? commande.getNumero() : -1;
    }

    public List<Commande> getAllCommandes() {
        return cr.findAll();
    }
    public List<Commande> getAllByEmail(String email) {
        return cr.findAllByEmail(email);
    }
}


