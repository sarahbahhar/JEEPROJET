package Servlet;

import DAO.CommandeDAO;
import DAO.ProduitCommandeDAO;
import DAO.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "OrderServlet", value = "/order-servlet")
public class OrderServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Récupérer les informations du formulaire
        String cardNumber = request.getParameter("numeroCarte");
        String expirationDate = request.getParameter("dateExpiration");
        String ccv = request.getParameter("ccv");

        // À ce stade, tu devrais valider les informations de la carte de crédit, par exemple, en utilisant un service de paiement ou une validation personnalisée.

        // Si les informations de la carte sont valides, procéder à la commande
        if (isCreditCardValid(cardNumber, expirationDate, ccv)) {
            // À implémenter : Effectuer les opérations nécessaires pour traiter la commande dans la base de données
            try {
                // 1. Création de la Commande
                CommandeDAO commandeDAO = new CommandeDAO();
                Commande commande = new Commande();
                commande.setDate(new Date());  // À ajuster selon la bdd
                // Autres informations de commande à définir

                int numeroCommande = commandeDAO.insertCommande(commande);

                // 2. Ajout des Produits Commandés
                List<LignePanier> panier = (List<LignePanier>) session.getAttribute("panier");
                ProduitCommandeDAO produitCommandeDAO = new ProduitCommandeDAO();

                for (LignePanier lignePanier : panier) {
                    Produitcommande produitCommande = new Produitcommande();
                    produitCommande.setProduit(lignePanier.getProduit());
                    produitCommande.setQuantite(lignePanier.getQuantite());
                    produitCommande.setCommandeNumero(numeroCommande);

                    produitCommandeDAO.insertProduitCommande(produitCommande);

                    // Mettre à jour le stock du produit
                    ProduitDAO produitDAO = new ProduitDAO();
                    Produit produit = lignePanier.getProduit();
                    produit.setStock(produit.getStock() - lignePanier.getQuantite());
                    produitDAO.updateProduit(produit); // a reregarder le fonction plus tard
                }

                // Une fois la commande traitée, on vide le panier
                session.removeAttribute("panier");

                // Rediriger vers une page de confirmation
                response.sendRedirect(request.getContextPath() + "/confirmation.jsp");
            } catch (Exception e) {
                e.printStackTrace(); // Gérer les erreurs
                response.sendRedirect(request.getContextPath() + "/erreur.jsp");
            }
        } else {
            // Si les informations de la carte ne sont pas valides, rediriger vers une page d'erreur
            response.sendRedirect(request.getContextPath() + "/erreur.jsp");
        }
    }

    private boolean isCreditCardValid(String cardNumber, String expirationDate, String ccv) {
        // a coder vérifier les informations entré pour la carte de crédit
        return true;
    }
}


