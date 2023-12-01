package DAO;

import java.util.Date;
import java.util.List;

import Model.Compte;
import Model.Comptebancaire;
import Model.Moderateur;
import Model.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ComptebancaireDAO
{

    /**
     * Add a bank account in the database
     * @param cb
     */
    public static void addComptebancaire(Model.Comptebancaire cb)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(cb);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete a bank account in the database
     * @param email
     * @param id
     */
    public void deleteComptebancaire(String email,String id){
        Comptebancaire cb;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        cb = (Comptebancaire) session.createQuery("FROM Comptebancaire cb WHERE cb.email = :email AND cb.id=:id").setParameter("email", email).setParameter("id", id).uniqueResult();
        session.delete(cb);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Get a list of bank card by an email
     * @param email
     * @return result
     */
    public static List<Comptebancaire> getListCBByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Comptebancaire> result = session.createQuery("FROM Comptebancaire cb WHERE cb.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }

    /**
     * validate a purchase
     * @param numero
     * @param cvv
     * @param date
     * @param id
     * @return boolean
     */

    public boolean validate(String numero, String cvv, String date, int id) {

        Transaction transaction = null;
        Comptebancaire compte = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            compte = (Comptebancaire) session.createQuery("FROM Comptebancaire Cb WHERE Cb.id = :id").setParameter("id", id).uniqueResult();

            if (compte != null && compte.getDate().equals(date) && compte.getCvv().equals(cvv)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }


}

