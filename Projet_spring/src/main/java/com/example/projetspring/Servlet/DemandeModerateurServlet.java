package com.example.projetspring.Servlet;

import DAO.DemandeModerateurDAO;
import Entity.Demandemoderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "demandeModeratorServlet", value = "/demande-moderator-servlet")
public class DemandeModerateurServlet extends HttpServlet {
    private DemandeModerateurDAO formModeratorDAO=new DemandeModerateurDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("WEB-INF/Vue/formAddModerator.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String description= request.getParameter("description");
            Demandemoderateur dM=new Demandemoderateur();
            dM.setEmail(email);
            dM.setMessage(description);
            formModeratorDAO.addModerator(dM);
            HttpSession session = request.getSession();
            session.setAttribute("demandeModerateur",true);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/myProfile.jsp");
            dispatcher.forward(request, response);




        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}
