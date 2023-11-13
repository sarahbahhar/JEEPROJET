package DAO;

import java.util.Date;
import java.util.List;

import Model.Comptebancaire;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ComptebancaireDAO
{
    public static void addComptebancaire(Comptebancaire c)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
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

