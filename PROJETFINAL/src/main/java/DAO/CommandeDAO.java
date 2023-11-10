package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Commande;

public class CommandeDAO {

    private Connection connection;

    public CommandeDAO() {
        this.connection = connection;
    }

    public int insertCommande(Commande commande) throws SQLException {
        String query = "INSERT INTO commande (date, ...other_columns...) VALUES (?, ...other_values...)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, new java.sql.Date(commande.getDate().getTime()));

            // Set other parameters based on your database schema

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating command failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating command failed, no ID obtained.");
                }
            }
        }
    }
}
