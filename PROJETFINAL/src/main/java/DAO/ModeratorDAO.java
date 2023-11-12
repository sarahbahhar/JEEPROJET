package DAO;

import Model.Client;
import Model.Infocompte;
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
    public static void removeModerator(String email)
    {
        Moderateur m;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        m = (Moderateur) session.createQuery("FROM Moderateur m WHERE m.email = :email").setParameter("email", email).uniqueResult();
        List list = session.createQuery("from Moderateur").list(); // a revoir
        session.delete(m);
        session.getTransaction().commit();
        session.close();
    }
    public static int[] getAllPermissionsByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        int[] allPermissions = new int[3]; // Utilisation d'un tableau d'entiers pour les autorisations

        try {
            Moderateur moderator = (Moderateur) session.createQuery("FROM Moderateur I WHERE I.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            if (moderator != null) {
                allPermissions[0] = moderator.getMaxProduitsLigne();
                allPermissions[1] = moderator.getPeutAjouterProduit();
                allPermissions[2] = moderator.getPeutSupprimerProduit();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return allPermissions;
    }

}
