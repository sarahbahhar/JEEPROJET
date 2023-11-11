package DAO;

import Model.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAO {



    // Generic method to check if an email exists in a specified table
    public static boolean emailExists(String email) {
        Transaction transaction = null;
        Client client = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Démarrez une transaction
            transaction = session.beginTransaction();
            // Obtenez un objet Client en recherchant par e-mail
            client = (Client) session.createQuery("FROM Client C WHERE C.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            // Si un client est trouvé, l'e-mail existe dans la base de données
            if (client != null) {
                return true;
            }

            // Commit la transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                // En cas d'erreur, vous pouvez effectuer un rollback de la transaction ici si nécessaire
            }
            e.printStackTrace();
        }
        return false;
    }



    // Other generic DAO methods to manage entities
}
