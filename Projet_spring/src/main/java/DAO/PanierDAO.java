package DAO;
import java.math.BigDecimal;
import java.util.List;

import Model.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PanierDAO {

    public static void createPanier(Panier p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    public static void updatePanier(String email, Panier updated){
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Panier toChange = (Panier) session.get(Panier.class, email);

            // Mettre à jour les champs de le panier existant avec les nouvelles valeurs

            toChange.setHt(updated.getHt());
            toChange.setTva(updated.getTva());
            toChange.setTtc(updated.getTtc());

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

    // Méthode pour récupérer les produits du panier
    public static List<Produitpanier> getListProduitpanier(String email) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produitpanier> result = session.createQuery("FROM Produitpanier P WHERE P.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }


    public static void ajouterProduitAuPanier(String email, int produitId, int quantite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Produit produit = ProduitDAO.getProduitById(produitId);


            if (produit != null && produit.getStock() >= quantite) {
                //produit.setStock(produit.getStock() - quantite);
                Produitpanier produitPanier = produitPanierExists(produit.getId(), email);

                if (produitPanier == null) {

                    produitPanier = new Produitpanier();
                    produitPanier.setEmail(email);
                    produitPanier.setProduitId(produitId);
                    produitPanier.setQuantite(quantite);
                    session.save(produitPanier);
                } else {
                    produitPanier.setQuantite(quantite + produitPanier.getQuantite());
                    session.update(produitPanier);
                }
            }
            session.update(produit);

            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Produitpanier produitPanierExists(Integer produitId, String email) {

        Transaction transaction = null;
        Produitpanier produitpanier = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Démarrez une transaction
            transaction = session.beginTransaction();
            // Obtenez un objet Client en recherchant par e-mail
            produitpanier = (Produitpanier) session.createQuery("FROM Produitpanier P WHERE P.email = :email AND P.produitId = :produitId ")
                    .setParameter("email", email)
                    .setParameter("produitId", produitId)
                    .uniqueResult();

            // Commit la transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                // En cas d'erreur, vous pouvez effectuer un rollback de la transaction ici si nécessaire
            }
            e.printStackTrace();
        }
        return produitpanier;
    }

/*
    public static void passerCommande(Commande commande, List<Produitcommande> produitsCommandes) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Enregistrez la commande dans la table Commande
            session.save(commande);

            // Associez les produits à la commande dans la table ProduitCommande
            for (Produitcommande produitCommande : produitsCommandes) {
                produitCommande.setCommande(commande);
                session.update(produitCommande);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
    // Pouvoir supprimer des produits du panier regarder ModeratorDAO


    public static void resetPanier(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        try {

            // Exécuter la requête pour obtenir l'objet panier
            Panier p = (Panier) session.createQuery("FROM Panier P WHERE P.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            p.setHt(BigDecimal.valueOf(0));
            p.setTva(BigDecimal.valueOf(0));
            p.setTtc(BigDecimal.valueOf(0));


            // Enregistrer les modifications dans la base de données
            session.update(p);
            transaction.commit();

            //le panier doit exister pour tous les utilisateurs
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

    public static void removeProduitPanier(String email) {


        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Produitpanier WHERE email = :email")
                .setParameter("email", email)
                .executeUpdate();


        session.getTransaction().commit();

        session.close();


    }

    public static void changeQuantityById(String email, int produitId, int newQuantity) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Produit produit = ProduitDAO.getProduitById(produitId);
            Produitpanier produitPanier = produitPanierExists(produitId, email);

            if (produit != null && produitPanier != null) {
                if (newQuantity == 0) {
                    session.delete(produitPanier);
                } else if (produit.getStock() >= newQuantity) {
                    produitPanier.setQuantite(newQuantity);
                    session.update(produitPanier);
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}



