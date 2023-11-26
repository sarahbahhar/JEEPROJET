package JEE.Servlet;

import DAO.ProduitDAO;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ModifyStockServlet", value = "/modify-stock-servlet")
public class ModifyStockServlet extends HttpServlet {
    private ProduitDAO produitDAO = new ProduitDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("produit_id");
            Integer produitId = Integer.parseInt(idStr);


            int newStock = Integer.parseInt(request.getParameter("stock")); // Stock modifié depuis le formulaire

            Produit produit = ProduitDAO.getProduitById(produitId);

            if (produit != null) {
                // Modifier le stock du produit
                produit.setStock(newStock);
                ProduitDAO.updateProduct(produit);

                // Redirection vers la page de gestion des produits
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/gererProduit.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (Exception e) {
            // rediriger vers la page d'erreur
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}

