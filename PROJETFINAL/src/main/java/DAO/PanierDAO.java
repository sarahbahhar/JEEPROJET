package DAO;
import java.util.List;

import Model.Client;
import Model.Commande;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Model.Produit;
import Model.Produitpanier;

public class PanierDAO {

    // Méthode pour récupérer les produits du panier
    public static List<Produitpanier> getListProduitpanier(String email) {

        Session session= HibernateUtil.getSessionFactory().openSession();
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
                produit.setStock(produit.getStock() - quantite);
                Produitpanier produitPanier = produitPanierExists(produit.getId() , email);

                if (produitPanier==null) {

                    produitPanier = new Produitpanier();
                    produitPanier.setEmail(email);
                    produitPanier.setProduitId(produitId);
                    produitPanier.setQuantite(quantite);
                    session.save(produitPanier);
                }
                    else{
                    produitPanier.setQuantite(quantite+produitPanier.getQuantite());
                    session.update(produitPanier);
                }
                }
                session.update(produit);

                session.getTransaction().commit();
                session.close();

            }
         catch (Exception e) {
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
}

