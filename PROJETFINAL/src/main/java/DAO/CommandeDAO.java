package DAO;

import java.util.List;

import Model.Commande;
import org.hibernate.Session;
import org.hibernate.Transaction;



public class CommandeDAO
{
    public static void addCommande(Commande c)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }





    public List<Commande> getListCompte() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Commande> result = session.createQuery("from Commande").list();
        session.close();
        return result;
    }
}

