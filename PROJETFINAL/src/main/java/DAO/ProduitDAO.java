package DAO;

import java.util.List;

import org.hibernate.Session;


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


    public Produit getProduitById(int productId) {
        return null;
    }

    public void updateProduit(Produit produit) {



    }
}

