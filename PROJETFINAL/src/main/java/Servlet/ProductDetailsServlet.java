package Servlet;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import DAO.ProduitDAO;
import Model.Produit;

@WebServlet(name = "ProductDetailsServlet", value = "/product-details")
public class ProductDetailsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String produitIdParam = request.getParameter("produit_id");
        String type = request.getParameter("type");

        if (produitIdParam != null) {
            int produitId = Integer.parseInt(produitIdParam);
            Produit produit = ProduitDAO.getProduitById(produitId);
            request.setAttribute("produit", produit);


        }
        RequestDispatcher dispatcher;
        if (Objects.equals(type, "pageProduit")){
            dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/pageProduit.jsp");

        }
        else {
            dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/pageProduitVendeur.jsp");
        }


        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}