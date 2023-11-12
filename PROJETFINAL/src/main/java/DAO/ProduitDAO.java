package DAO;

import java.io.File;
import java.util.List;

import Model.Moderateur;
import Model.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class ProduitDAO
{
    public static final String PATH_IMAGE = "/img/";


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

    public static void removeProductById(int id)
    {
        Produit p;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        p = getProduitById(id);
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



    public static Produit getProduitById(int produitId) {
        Produit produit = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            produit = session.get(Produit.class, produitId);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }

    public void updateProduit(Produit produit) {



    }
}

