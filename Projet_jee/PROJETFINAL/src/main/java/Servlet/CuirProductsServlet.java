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

@WebServlet(name = "CuirProductsServlet", value = "/cuir-products")
public class CuirProductsServlet extends HttpServlet {
    private final ProduitDAO productDAO = new ProduitDAO();

    /**
     * get information form a get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> cuirProducts = productDAO.getProductsByCategory("Cuir");
        request.setAttribute("cuirProducts", cuirProducts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/cuir.jsp");
        dispatcher.forward(request, response);
    }
}
