package DAO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import Entity.Commentaires;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository

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
    public List<Integer> getBestNotedIdProduct(){
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Integer> result = session.createQuery("SELECT idProduit, AVG(note) AS moyenne\n" +
                "FROM Commentaires \n" +
                "GROUP BY idProduit\n" +
                "ORDER BY moyenne ASC").list();
        session.close();
        return null;
    }

    public static boolean hasCommented(int produitId, String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        boolean hasCommented=false;
        long nbComment;
        try {
            transaction= session.beginTransaction();
            nbComment= (long) session.createQuery("SELECT COUNT(*) FROM Commentaires C WHERE C.email = :email AND C.idProduit = :idProduit")
                    .setParameter("email", email)
                    .setParameter("idProduit", produitId)
                    .uniqueResult();
            hasCommented=(nbComment>0);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return hasCommented;
    }


}
