package DAO;

import Model.Client;
import Model.Infocompte;
import Model.Moderateur;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.time.LocalDate;



public class ModeratorDAO {
    public List<Moderateur> getListModerateur() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Moderateur> result = session.createQuery("from Moderateur", Moderateur.class).list();
        session.close();
        return result;
    }

    public static void addModerator(Model.Moderateur m) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        session.close();
    }

    public static boolean emailExists(String email) {
        Transaction transaction = null;
        Moderateur moderator = null;
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

    public static void removeModerator(String email) {
        Moderateur m;
        Session session = HibernateUtil.getSessionFactory().openSession();
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
        String email = updated.getEmail();
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

    public BigDecimal getAverageRatingByEmail(String email) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT AVG(c.note) FROM Commentaires c WHERE c.emailVendeur = :email");
            query.setParameter("email", email);
            Double result = (Double) query.uniqueResult();

            if (result != null) {
                return BigDecimal.valueOf(result);
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    public static void bannirByEmail(String email, String motifCourt, String motifLong, String dateStr) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Moderateur moderator = (Moderateur) session.createQuery("FROM Moderateur M WHERE M.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            if (moderator != null) {
                moderator.setMotifCourtBannissement(motifCourt);
                moderator.setMotifLongBanissement(motifLong);
                moderator.setNbBannissement(moderator.getNbBannissement() + 1);
                moderator.setPeutAjouterProduit((byte) 0);
                moderator.setPeutSupprimerProduit((byte) 0);
                ModeratorDAO.updateDateBanni(moderator,dateStr);
                session.update(moderator);
                transaction.commit();
            } else {
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    public static void unbanByEmail(String email) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Moderateur moderator = (Moderateur) session.createQuery("FROM Moderateur M WHERE M.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            java.util.Date currentDate = new java.util.Date();

            java.sql.Date currentSqlDate = new java.sql.Date(currentDate.getTime());

            if (moderator != null && moderator.getDateBanni().before(currentSqlDate)) {
                moderator.setMotifCourtBannissement(null);
                moderator.setMotifLongBanissement(null);
                moderator.setPeutAjouterProduit((byte) 1);
                moderator.setPeutSupprimerProduit((byte) 1);
                moderator.setDateBanni(null);
                session.update(moderator);
                transaction.commit();
            } else {
                System.out.println("Aucun modérateur trouvé avec l'email : " + email);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    private static void updateDateBanni(Moderateur moderator, String dateStr) {
        LocalDate localDate = LocalDate.parse(dateStr); // Conversion de la chaîne de caractères en LocalDate
        Date date = Date.valueOf(localDate); // Conversion de LocalDate en java.sql.Date
        moderator.setDateBanni(date); // Mise à jour de l'objet moderateur
    }
}
