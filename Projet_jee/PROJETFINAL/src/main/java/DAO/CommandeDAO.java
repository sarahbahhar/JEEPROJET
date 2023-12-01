package DAO;

import java.sql.SQLException;
import java.util.List;

import Model.Infocompte;
import org.hibernate.Transaction;
import org.hibernate.Session;
import Model.Commande;
public class CommandeDAO {

    /**
     * Get a list of products by an email
     * @param email
     * @return result
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

    /**
     * Get a list of order by a seller's Email
     * @param email
     * @return a list
     */
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

    /**
     * Add an order to the database
     * @param c
     * @throws SQLException
     */
    public static void addCommande(Commande c) throws SQLException
    {

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * Get the last order by the id for an email
     * @param email
     * @return nCommande
     */
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


    /**
     * get a list of account
     * @return result
     */
    public List<Commande> getListCompte() {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Commande> result = session.createQuery("from Commande").list();
        session.close();
        return result;
    }
}

