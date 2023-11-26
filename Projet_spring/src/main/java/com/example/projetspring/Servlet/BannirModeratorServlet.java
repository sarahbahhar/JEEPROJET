package com.example.projetspring.Servlet;

import DAO.ModeratorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bannir-moderator-servlet")
public class BannirModeratorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String motifCourt = request.getParameter("motifCourt");
        String motifLong = request.getParameter("motifLong");
        String dateFinBan = request.getParameter("dateFinBan");


        ModeratorDAO.bannirByEmail(email, motifCourt, motifLong,dateFinBan);

        // Redirection ou gestion de la réponse après le bannissement

        response.sendRedirect(request.getContextPath()+"/moderator-servlet"); // changer type=page  produti et idproduit s'inspirer de produitCommande

    }
}
