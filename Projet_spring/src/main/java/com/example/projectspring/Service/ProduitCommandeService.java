package com.example.projectspring.Service;


import com.example.projectspring.Repository.ProduitCommandeRepository;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Entity.Produitcommande;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitCommandeService {

    @Autowired
    private ProduitCommandeRepository pcr;

    public List<Produitcommande> getListProduitCommande(int idCommande) {
        return pcr.findByCommandeNumero(idCommande);
    }
    public void insertProduitCommande(Produitcommande produitCommande) {
        pcr.save(produitCommande);
    }

}
