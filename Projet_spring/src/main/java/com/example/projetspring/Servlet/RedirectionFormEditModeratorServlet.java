package com.example.projetspring.Servlet;

import DAO.ModeratorDAO;
import Entity.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")

@WebServlet(name = "RedirectionFormEditModeratorServlet", value = "/redirection-form-edit-moderator-servlet")

public class RedirectionFormEditModeratorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");

            Moderateur moderator = ModeratorDAO.getModeratorByEmail(email);
            request.setAttribute("moderator", moderator);



        }catch (Exception e)
        {response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();}
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/formEditModerateur.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            response.sendRedirect(request.getContextPath() + "/error");
            throw new RuntimeException(e);
        }
    }
}

