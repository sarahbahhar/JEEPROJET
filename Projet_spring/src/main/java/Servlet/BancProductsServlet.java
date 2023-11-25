package Servlet;

import DAO.ProduitDAO;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BancProductsServlet", value = "/banc-products")
public class BancProductsServlet extends HttpServlet {
    private final ProduitDAO productDAO = new ProduitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> bancProducts = productDAO.getProductsByCategory("Banc");
        request.setAttribute("bancProducts", bancProducts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/banc.jsp");
        dispatcher.forward(request, response);
    }
}
