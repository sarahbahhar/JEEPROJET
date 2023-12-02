package DAO;

import Model.Client;
import Model.Demandemoderateur;
import Model.Infocompte;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerDAO {


    /**
     * verify if email exist for a customer
     * @param email
     * @return boolean
     */
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

            }
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Remove a customer in the database
     * @param email
     */
    public static void removeCustomer(String email)
    {
        Client c;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        c = (Client) session.createQuery("FROM Client c WHERE c.email = :email").setParameter("email", email).uniqueResult();
        List list = session.createQuery("from Client").list();
        session.delete(c);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Add a new customer to the database
     * @param c
     */
    public static void addCustomer(Model.Client c)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Add fidelity point to a customer
     * @param email
     * @param point
     */
    public static void addPointFidelite(String email, int point){
        Transaction transaction = null;
        Client client = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            client = (Client) session.createQuery("FROM Client C WHERE C.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();
            int newPts = (client.getPointsFidelite()+point);
            client.setPointsFidelite(newPts);

            session.update(client);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    /**
     * Get a customer in the database
     * @param email
     * @return Client
     */
    public static Client getClient(String email){
        Client c= null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            c = (Client) session.createQuery("FROM Client WHERE email = :email").setParameter("email", email).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }


        return c;
    }

    /**
     * Update the information for an account
     * @param email
     * @param updated
     */
    public static void updateClient(String email, Client updated) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Charger le client existant à partir de la base de données
            Client toChange = (Client) session.get(Client.class, email);

            // Mettre à jour les champs de client existant avec les nouvelles valeurs

            toChange.setPointsFidelite(updated.getPointsFidelite());

            // Enregistrer les modifications dans la base de données
            session.update(toChange);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
