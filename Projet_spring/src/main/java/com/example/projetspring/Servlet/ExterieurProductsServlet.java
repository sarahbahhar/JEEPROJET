package com.example.projetspring.Servlet;

import DAO.ProduitDAO;
import Entity.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "ExterieurProductsServlet", value = "/exterieur-products")
public class ExterieurProductsServlet extends HttpServlet {
    private final ProduitDAO produitDAO = new ProduitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> exterieurProducts = produitDAO.getProductsByCategory("Exterieur");
        request.setAttribute("exterieurProducts", exterieurProducts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/exterieur.jsp");
        dispatcher.forward(request, response);
    }
}
