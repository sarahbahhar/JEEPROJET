package com.example.projectspring.Service;


import com.example.projectspring.Entity.Client;
import com.example.projectspring.Entity.Compte;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.ModeratorRepository;
import com.example.projectspring.Entity.Moderateur;

import java.util.List;


@Service

public class ModeratorService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private ModeratorRepository mr;




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

    public List<Moderateur> getListModerateur() {
        List<Moderateur> result = mr.findAll();
        return result;
    }

    public Moderateur getModeratorByEmail(String email) {
        Moderateur m= null;
        try {
            m = mr.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public void addModerateur(Moderateur m) {
        try{
            mr.save(m);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean emailExists(String email) {

        Moderateur m = null;
        try {
            m = mr.findByEmail(email);
            if (m!= null) {
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeModerator(String localisation,String email) {
        try {
            mr.updateVendeur(email);

            ProduitDAO.removeProductByModerator(localisation,email);
            Moderateur moderator = session.get(Moderateur.class, email);
            if (moderator != null) session.delete(moderator);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }



    public void addPointFidelite(String email, int point){

        Client client = null;
        try{
            client = cr.findByEmail(email);

            int newPts = (client.getPointsFidelite()+point);
            client.setPointsFidelite(newPts);
            cr.addPoint(newPts, client.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClient(String email){
        Client c= null;

        try {
            c = cr.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    public void updateClient(String email, Client updated) {
        try {
            // Charger le client existant à partir de la base de données
            Client toChange = cr.findByEmail(email);

            // Mettre à jour les champs de client existant avec les nouvelles valeurs

            int points=updated.getPointsFidelite();
            cr.addPoint(points, email);
            // Enregistrer les modifications dans la base de données


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}