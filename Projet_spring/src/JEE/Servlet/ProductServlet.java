package JEE.Servlet;
import java.io.IOException;

import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import DAO.ProduitDAO;
import Model.Produit;

@WebServlet(name = "ProductServlet", value = "/product-servlet")
public class ProductServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String email = request.getParameter("email");


        List<Produit> listProduit = ProduitDAO.getListProduit();

            // Set the list of products as an attribute in the request
        request.setAttribute("produits", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/productList.jsp");



        try {

            dispatcher.forward(request, response);


        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String email = request.getParameter("email");

            if (email != null) {
                List<Produit> listProduit = ProduitDAO.getListProduitByEmail(email);

                // Set the list of products as an attribute in the request
                request.setAttribute("produits", listProduit);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/gererProduit.jsp");
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }

            }
            }

            }



