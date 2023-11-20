package DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import Model.Commentaires;
import jakarta.persistence.Query;
import org.hibernate.Session;

public class CommentairesDAO {
    public static void addCommentaire(Commentaires commentaire) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(commentaire);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static List<Commentaires> getCommentairesByProduitId(int produitId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Commentaires WHERE idProduit = :produitId", Commentaires.class)
                    .setParameter("produitId", produitId)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Gérer les erreurs
        }
    }

    public static BigDecimal getNoteMoyenneProduit(int produitId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("SELECT AVG(c.note) FROM Commentaires c WHERE c.idProduit = :produitId");
            query.setParameter("produitId", produitId);
            Object result = query.getSingleResult();

            if (result != null) {
                // Convertir en BigDecimal si possible
                if (result instanceof BigDecimal) {
                    return (BigDecimal) result;
                } else if (result instanceof Double) {
                    // Convertir en BigDecimal si le résultat est un Double
                    return BigDecimal.valueOf((Double) result);
                } else {
                    // Autres conversions possibles selon le type de résultat
                    // Fournir la logique de conversion nécessaire ici
                    return BigDecimal.ZERO;
                }
            } else {
                return BigDecimal.ZERO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
