package DAO;
import java.util.List;

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

    /*

    // Méthode pour ajouter un produit au panier avec une quantité spécifiée
    public static void ajouterProduitAuPanier(int produitId, int quantite) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Produit produit = session.get(Produit.class, produitId);

            if (produit != null) {
                ProduitPanier produitPanier = new ProduitPanier();
                produitPanier.setProduitId(produit);
                produitPanier.setQuantite(quantite);

                session.save(produitPanier);
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

