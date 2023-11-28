package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Infocompte;
import org.hibernate.Transaction;
import org.hibernate.Session;
import Model.Commande;
public class CommandeDAO

{

/*
    public static void addProduct(Produit p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    */


    public static List<Commande> getListProduitByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List result = session.createQuery("FROM Commande C WHERE C.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }
    public static List<Commande> getListOrderByEmailSeller(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Commande> result = session.createQuery(
                        "SELECT DISTINCT C FROM Commande C " +
                                "WHERE C.numero IN (SELECT PC.commandeNumero FROM Produitcommande PC WHERE PC.emailVendeur = :email)")
                .setParameter("email", email)
                .list();

        session.close();
        return result;
    }


    public static void addCommande(Commande c) throws SQLException
    {

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();

        session.close();
    }

    public int getLastCommandeIdByEmail(String email) {
        int nCommande=-1;
        Commande c= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            c = (Commande) session.createQuery("FROM Commande WHERE email = :email ORDER BY numero DESC LIMIT 1").setParameter("email", email).setMaxResults(1).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        if(c!=null){
            nCommande=c.getNumero();
        }
        return nCommande;
    }




    public List<Commande> getListCompte() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Commande> result = session.createQuery("from Commande").list();
        session.close();
        return result;
    }
}

