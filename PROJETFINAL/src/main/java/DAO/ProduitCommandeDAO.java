package DAO;

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
    public static List<Produitcommande> getListProduitCommande(int idCommande) {

        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produitcommande> result = session.createQuery("FROM Produitcommande P WHERE P.commandeNumero = :idCommande")
                .setParameter("idCommande", idCommande).list();
        session.close();
        return result;
    }

    public void insertProduitCommande(Produitcommande produitCommande) throws SQLException {
        /*String query = "INSERT INTO produit_commande (produit_id, commande_numero, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, produitCommande.getId());
            preparedStatement.setInt(2, produitCommande.getCommandeNumero());
            preparedStatement.setInt(3, produitCommande.getQuantite());

            preparedStatement.executeUpdate();
        }*/

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(produitCommande);
        session.getTransaction().commit();
        session.close();
    }
}
