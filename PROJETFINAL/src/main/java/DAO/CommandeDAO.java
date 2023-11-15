package DAO;

import java.util.List;


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
/*
    public static void removeProductById(int id)
    {
        Produit p;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Produitpanier WHERE produitId = :id")
                .setParameter("id", id)
                .executeUpdate();

        p = getProduitById(id);
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }*/
    /*
    public static List<Produit> getListProductByTitre(String titre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("FROM Produit P WHERE P.titre LIKE :titre")
                .setParameter("titre", "%" + titre + "%").list();
        session.close();
        return result;
    }*/


    //
    /*
    public static Produit getProduitById(int produitId) {
        Produit produit = null;

        try
                (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            produit = session.get(Produit.class, produitId);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }*/


}

