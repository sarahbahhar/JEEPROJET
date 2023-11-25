package Servlet;

import DAO.PanierDAO;
import Model.Produitpanier;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ModifyQuantityServlet", value = "/modify-quantity-servlet")
public class ModifyQuantityServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("produit_id");
            Integer produit_id = Integer.parseInt(idStr);
            String email = request.getParameter("email");
            Integer newQuantity = Integer.parseInt(request.getParameter("quantite"));


            PanierDAO.changeQuantityById(email,produit_id,newQuantity);



            response.sendRedirect(request.getContextPath()+"/panier-servlet?email="+email);




        } catch (Exception e) {
            // rediriger vers la page d'erreur
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}

