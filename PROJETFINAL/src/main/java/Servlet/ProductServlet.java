package Servlet;
import java.io.IOException;

import DAO.CompteDAO;
import DAO.ModeratorDAO;
import Model.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import DAO.ProduitDAO;
import Model.Produit;

@WebServlet(name = "ProductServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitDAO test = new ProduitDAO();
        List<Produit> listProduit = test.getListProduit();
        request.setAttribute("produits",listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/productList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {



    }
}
