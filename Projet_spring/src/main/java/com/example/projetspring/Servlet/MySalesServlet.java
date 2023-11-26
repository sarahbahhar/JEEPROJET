package com.example.projetspring.Servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import DAO.CommandeDAO;
import Entity.Commande;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "MySalesServlet", value = "/my-sales-servlet")
public class MySalesServlet extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");

        if (email != null) {
            List<Commande> salesList = CommandeDAO.getListOrderByEmailSeller(email);

            // Set the list of products as an attribute in the request
            request.setAttribute("commandes", salesList); // recalculer le total
            request.setAttribute("type", "pageMySales");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/mesCommandes.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

        }
    }

}



