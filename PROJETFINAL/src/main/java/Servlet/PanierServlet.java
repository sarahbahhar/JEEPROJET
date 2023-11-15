package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProduitDAO;
import Model.Produit;
import Model.Produitcommande;
import DAO.PanierDAO;

import Model.Produitpanier;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "PanierServlet", value = "/panier-servlet")
public class PanierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        ProduitDAO prduitDAO = new ProduitDAO();
        List<Produitpanier> panier = PanierDAO.getListProduitpanier(email);
        List<Produit> produitsDansPanier = new ArrayList<>();

        double total = 0.0;

        for (Produitpanier produitpanier : panier) {
            // Obtenez les détails du produit associé à Produitcommande depuis la base de données
            Produit produit = ProduitDAO.getProduitById(produitpanier.getProduitId());

            // Vérifiez si le produit existe avant de l'utiliser
            if (produit != null) {
                total += produit.getPrix().doubleValue() * produitpanier.getQuantite();

                produitsDansPanier.add(produit);
            }
        }

        request.setAttribute("panier", panier);
        request.setAttribute("total", total);
        request.setAttribute("produitsDansPanier", produitsDansPanier);
        HttpSession session = request.getSession();
        session.setAttribute("total",total);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/panier.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
/*
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");

        if (action != null && action.equals("ajouterPanierProduit")) {
            // Récupérez l'ID du produit et la quantité à ajouter au panier depuis le formulaire
            int produitId = Integer.parseInt(request.getParameter("produitId"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));

            // Ajoutez le produit au panier (ajustez cela en fonction de votre logique)
            PanierDAO.ajouterProduitAuPanier(produitId, quantite);

            // Redirigez vers la page d'affichage des produits ou une autre page selon vos besoins
            response.sendRedirect("/votre-contexte/affichageProduits.jsp");
        } else {
            // Logique de traitement du formulaire POST (si nécessaire)
        }
    }

 */
}
