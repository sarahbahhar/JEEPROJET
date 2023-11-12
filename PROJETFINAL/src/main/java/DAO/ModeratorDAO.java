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
    public void updateModerator(Moderateur updated) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String email= updated.getEmail();
        try {

            // Exécuter la requête pour obtenir l'objet Moderateur
            Moderateur moderator = (Moderateur) session.createQuery("FROM Moderateur M WHERE M.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            // Vérifier si l'objet existe
            if (moderator != null) {
                // Mettre à jour les champs de l'objet Moderateur avec les nouvelles valeurs
                moderator.setPeutAjouterProduit(updated.getPeutAjouterProduit());
                moderator.setPeutSupprimerProduit(updated.getPeutSupprimerProduit());
                moderator.setMaxProduitsLigne(updated.getMaxProduitsLigne());

                // Enregistrer les modifications dans la base de données
                session.update(moderator);
                transaction.commit();
            } else {
                // Gérer le cas où l'objet Moderateur n'a pas été trouvé
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
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
