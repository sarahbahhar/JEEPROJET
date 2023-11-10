package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProduitCommandeDAO {

    private Connection connection;

    public ProduitCommandeDAO() {
        this.connection = connection;
    }

    public void insertProduitCommande(Produitcommande produitCommande) throws SQLException {
        String query = "INSERT INTO produit_commande (produit_id, commande_numero, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, produitCommande.getProduitId());
            preparedStatement.setInt(2, produitCommande.getCommandeNumero());
            preparedStatement.setInt(3, produitCommande.getQuantite());

            preparedStatement.executeUpdate();
        }
    }
}
