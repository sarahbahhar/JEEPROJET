package DAO;

import Model.Moderateur;
import Model.Produitcommande;
import Model.Produitpanier;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProduitCommandeDAO {

    private Connection connection;

    public ProduitCommandeDAO() {
        this.connection = connection;
    }

    /**
     * getList of order products
     * @param idCommande
     * @return list of order product
     */
    public static List<Produitcommande> getListProduitCommande(int idCommande) {

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produitcommande> result = session.createQuery("FROM Produitcommande P WHERE P.commandeNumero = :idCommande")
                .setParameter("idCommande", idCommande).list();
        session.close();
        return result;
    }

    /**
     * getList of order products
     * @return list of ordfer product
     */
    public static List<Produitcommande> getListProduitCommand() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produitcommande> result = session.createQuery("from Produitcommande ", Produitcommande.class).list();
        session.close();
        return result;
    }

    /**
     * insert product to an order
     * @param produitCommande
     * @throws SQLException
     */
    public void insertProduitCommande(Produitcommande produitCommande) throws SQLException {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(produitCommande);
        session.getTransaction().commit();
        session.close();
    }
}
