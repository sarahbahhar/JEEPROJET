package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.ProduitDAO;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "CartServlet", value = "/cart-servlet")
public class CartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Récupérer les informations du formulaire
        int productId = Integer.parseInt(request.getParameter("produitId"));
        int quantity = Integer.parseInt(request.getParameter("quantite"));

        // Récupérer le panier depuis la session
        List<LignePanier> cart = (List<LignePanier>) session.getAttribute("panier");

        // Si le panier n'existe pas, le créer
        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("panier", cart);
        }

        // Ajouter le produit au panier
        Produit produit = getProductById(productId);  // À implémenter : récupérer le produit depuis la base de données
        LignePanier lignePanier = new LignePanier(produit, quantite);
        cart.add(lignePanier);

        // Rediriger vers la page productList.jsp
        response.sendRedirect(request.getContextPath() + "/product-servlet");
    }

    private Produit getProductById(int productId) {
        // À implémenter : récupérer le produit depuis la base de données en utilisant ProduitDAO
        ProduitDAO produitDAO = new ProduitDAO();
        return produitDAO.getProduitById(productId);
    }
}

