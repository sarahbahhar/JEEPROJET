package Servlet;

import DAO.DemandeModerateurDAO;
import Model.Demandemoderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

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
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/formAddModerator.jsp");
            dispatcher.forward(request, response);




        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
