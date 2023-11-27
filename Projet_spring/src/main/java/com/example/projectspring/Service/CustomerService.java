package com.example.projectspring.Service;


import com.example.projectspring.Entity.Compte;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.CustomerRepository;
import com.example.projectspring.Entity.Client;



@Service

public class CustomerService {


    /*
    private CustomerService cs;
    private CreditCardService ccs;
    private ProductService ps;
    private UserService us;
    */
    @Autowired
    private CustomerRepository cr;




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


    public boolean emailExists(String email) {

        Client client = null;
        try {
            client = cr.findByEmail(email);
            if (client!= null) {
                return true;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void removeCustomer(String email)
    {
        Client c;
        //c = cr.findByEmail(email);
        try{
            cr.deleteByEmail(email);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void addCustomer(Client client) {
        try{
            cr.save(client);
        }catch(Exception e){
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