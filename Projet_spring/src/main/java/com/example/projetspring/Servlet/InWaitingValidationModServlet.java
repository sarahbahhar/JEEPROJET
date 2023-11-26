package com.example.projetspring.Servlet;

import DAO.DemandeModerateurDAO;
import Entity.Demandemoderateur;
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
@WebServlet(name = "inWaitingModServlet", value = "/in-waiting-mod-servlet")
public class InWaitingValidationModServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DemandeModerateurDAO test = new DemandeModerateurDAO();
        List<Demandemoderateur> listModerator = test.getListModerateurWaiting();
        request.setAttribute("moderators",listModerator);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/moderatorWaitingList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
