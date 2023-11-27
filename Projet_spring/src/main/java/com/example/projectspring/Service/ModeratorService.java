package com.example.projectspring.Service;


import com.example.projectspring.Entity.Client;
import com.example.projectspring.Entity.Compte;
import com.mysql.cj.protocol.a.CompressedInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.example.projectspring.Repository.ModeratorRepository;
import com.example.projectspring.Repository.ProduitRepository;
import com.example.projectspring.Entity.Moderateur;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

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
    private ProduitService ps;





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

            ps.removeProductByModerator(localisation,email);///////////
            Moderateur moderator = mr.findByEmail(email);
            if (moderator != null) {
                mr.deleteByEmail(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void updateModerator(String email, Moderateur updated) {
        try {
            // Charger le client existant à partir de la base de données
            Moderateur moderator = mr.findByEmail(email);
            if(moderator!=null){
                moderator.setPeutAjouterProduit(updated.getPeutAjouterProduit());
                moderator.setPeutSupprimerProduit(updated.getPeutSupprimerProduit());
                moderator.setMaxProduitsLigne(updated.getMaxProduitsLigne());
                mr.save(moderator);//////////
            }
            else {
                // Gérer le cas où l'objet Moderateur n'a pas été trouvé
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BigDecimal getAverageRatingByEmail(String email) {
        try {

            Double result = mr.getAverageRatingByEmail(email);

            if (result != null) {
                return BigDecimal.valueOf(result);
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public void bannirByEmail(String email, String motifCourt, String motifLong, String dateStr) {

        try {
            Moderateur moderator = mr.findByEmail(email);

            if (moderator != null) {
                moderator.setMotifCourtBannissement(motifCourt);
                moderator.setMotifLongBanissement(motifLong);
                moderator.setNbBannissement(moderator.getNbBannissement() + 1);
                updateDateBanni(moderator,dateStr);
                mr.save(moderator);//////
            } else {
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unbanByEmail(String email) {
        try {
            Moderateur moderator = mr.findByEmail(email);
            // Vérifier d'abord si le modérateur est trouvé
            if (moderator != null) {
                // Vérifier ensuite si la date de bannissement est non nulle et dans le passé
                java.util.Date currentDate = new java.util.Date();
                java.sql.Date currentSqlDate = new java.sql.Date(currentDate.getTime());

                if (moderator.getDateBanni() != null && moderator.getDateBanni().before(currentSqlDate)) {
                    moderator.setMotifCourtBannissement(null);
                    moderator.setMotifLongBanissement(null);
                    moderator.setDateBanni(null);
                    mr.save(moderator);///////////
                } else {
                    System.out.println("Le modérateur n'est pas banni ou la date de bannissement est dans le futur : " + email);
                }
            } else {
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateDateBanni(Moderateur moderator, String dateStr) {
        LocalDate localDate = LocalDate.parse(dateStr); // Conversion de la chaîne de caractères en LocalDate
        Date date = Date.valueOf(localDate); // Conversion de LocalDate en java.sql.Date
        moderator.setDateBanni(date); // Mise à jour de l'objet moderateur
    }


}