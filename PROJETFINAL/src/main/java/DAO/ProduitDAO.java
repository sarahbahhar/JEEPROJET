package DAO;

import java.io.File;
import java.util.List;

import Model.Moderateur;
import Model.Produit;
import org.hibernate.Session;
import Model.Produit;

public class ProduitDAO
{


    public static void addProduct(Produit p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    public static List<Produit> getListProduit()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("from Produit").list();
        session.close();
        return result;
    }
    public static List<Produit> getListProduitByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("FROM Produit P WHERE P.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }

    public static void removeProduct(String email)
    {
        Produit p;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        p = (Produit) session.createQuery("FROM Produit m WHERE m.email = :email").setParameter("email", email).uniqueResult();
        List list = session.createQuery("from Produit").list();
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    public static List<Produit> getListProductByTitre(String titre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("FROM Produit P WHERE P.titre LIKE :titre")
                .setParameter("titre", "%" + titre + "%").list();
        session.close();
        return result;
    }



    public Produit getProduitById(int productId) {
        return null;
    }

    public void updateProduit(Produit produit) {



    }
}

