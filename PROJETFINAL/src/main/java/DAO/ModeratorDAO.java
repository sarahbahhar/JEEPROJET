package DAO;

import Model.Moderateur;
import org.hibernate.Session;
import java.util.List;
import org.hibernate.Transaction;

public class ModeratorDAO {
    public List<Moderateur> getListModerateur() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Moderateur> result = session.createQuery("from Moderateur", Moderateur.class).list();
        session.close();
        return result;
    }
    public static void addModerator(Model.Moderateur m)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        session.close();
    }




    public static boolean emailExists(String email) {
        Transaction transaction = null;
        Moderateur  moderator = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Démarrez une transaction
            transaction = session.beginTransaction();
            // Obtenez un objet Client en recherchant par e-mail
            moderator = (Moderateur) session.createQuery("FROM Moderateur C WHERE C.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            // Si un client est trouvé, l'e-mail existe dans la base de données
            if (moderator != null) {
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
}