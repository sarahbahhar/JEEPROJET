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
@WebServlet(name = "TissuProductsServlet", value = "/tissu-products")
public class TissuProductsServlet extends HttpServlet {
    private final ProduitDAO produitDAO = new ProduitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produit> tissuProducts = produitDAO.getProductsByCategory("Tissu");
        request.setAttribute("tissuProducts", tissuProducts);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/tissu.jsp");
        dispatcher.forward(request, response);
    }
}

