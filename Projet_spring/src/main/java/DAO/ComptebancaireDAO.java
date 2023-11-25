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
    public static void addComptebancaire(Model.Comptebancaire cb)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(cb);
        session.getTransaction().commit();
        session.close();
    }
    public void deleteComptebancaire(String email,String numero){
        Comptebancaire cb;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        cb = (Comptebancaire) session.createQuery("FROM Comptebancaire cb WHERE cb.email = :email AND cb.numero=:numero").setParameter("email", email).setParameter("numero", numero).uniqueResult();
        session.delete(cb);
        session.getTransaction().commit();
        session.close();
    }
    public static List<Comptebancaire> getListCBByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Comptebancaire> result = session.createQuery("FROM Comptebancaire cb WHERE cb.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }


    public boolean validate(String numero, String cvv, String date) {

        Transaction transaction = null;
        Comptebancaire compte = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            compte = (Comptebancaire) session.createQuery("FROM Comptebancaire Cb WHERE Cb.numero = :numero").setParameter("numero", numero).uniqueResult();

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

