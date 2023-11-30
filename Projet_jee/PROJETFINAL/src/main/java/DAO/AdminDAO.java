package DAO;

import Model.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AdminDAO {


    /**
     *emailExists
     * @param email
     * @return
     */
    public static boolean emailExists(String email) {
        Transaction transaction = null;
        Admin admin = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Démarrez une transaction
            transaction = session.beginTransaction();
            // Obtenez un objet Client en recherchant par e-mail
            admin = (Admin) session.createQuery("FROM Admin C WHERE C.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            // Si un client est trouvé, l'e-mail existe dans la base de données
            if (admin != null) {
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
     *getAdminEmail
     * @return adminEmail
     */
    public static String getAdminEmail() {
        Transaction transaction = null;
        String adminEmail = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Admin admin = (Admin) session.createQuery("FROM Admin").setMaxResults(1).uniqueResult();
            if (admin != null) {
                adminEmail = admin.getEmail();
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return adminEmail;
    }
}
