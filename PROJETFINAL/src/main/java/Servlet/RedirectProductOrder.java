package Servlet;

import DAO.ProduitDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RedirectProductOrder", value = "/redirect-product-order-servlet")
public class RedirectProductOrder extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String produitIdStr = request.getParameter("produit_id");
        int produitId = Integer.parseInt(produitIdStr);

        if(ProduitDAO.getProduitById(produitId)!=null){
            response.sendRedirect(request.getContextPath()+"/product-details?type=pageProduit&produit_id="+produitId);

        }

        else{
            response.sendRedirect(request.getContextPath()+"/WEB-INF/Vue/produitSupprime.jsp");

        }
    }
}
