package com.example.projetspring.Servlet;


import java.io.IOException;
import java.util.List;

import DAO.ProduitCommandeDAO;
import Entity.Produitcommande;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/commande-detail-servlet")
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