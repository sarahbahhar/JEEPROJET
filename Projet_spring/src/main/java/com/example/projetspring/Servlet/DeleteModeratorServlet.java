package com.example.projetspring.Servlet;

import DAO.CustomerDAO;
import DAO.ModeratorDAO;
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
@WebServlet(name = "DeleteModeratorServlet", value = "/delete-moderator-servlet")
public class
DeleteModeratorServlet extends HttpServlet {
    private ModeratorDAO ModDAO=new ModeratorDAO();
    private CustomerDAO CustomDAO=new CustomerDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("moderatorList.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            if(email != null){
                ModeratorDAO.removeModerator(getServletContext().getRealPath("/"),email);

                response.sendRedirect(request.getContextPath() + "/moderator-servlet");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}