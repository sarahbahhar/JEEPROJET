package JEE.Servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import DAO.ProduitCommandeDAO;
import Model.Produitcommande;
import Model.Produit;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "CommandeDetailServlet", value = "/commande-detail-servlet")
public class CommandeDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

    String idCommandeStr = request.getParameter("commande_id");
    int idCommande = Integer.parseInt(idCommandeStr);

    List<Produitcommande> listProduitCommande = ProduitCommandeDAO.getListProduitCommande(idCommande);

    request.setAttribute("produitcommandes", listProduitCommande);


    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/produitCommande.jsp");
        try {
        dispatcher.forward(request, response);
    } catch (ServletException e) {
        throw new RuntimeException(e);
    }
}




}