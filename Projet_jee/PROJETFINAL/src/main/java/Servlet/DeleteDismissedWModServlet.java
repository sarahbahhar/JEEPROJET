package Servlet;

import DAO.DemandeModerateurDAO;
import Model.Compte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "DeleteDismissedWModServlet", value = "/delete-dismissed-mod-servlet")
public class DeleteDismissedWModServlet extends HttpServlet {
    private DemandeModerateurDAO DModeratorDAO=new DemandeModerateurDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("moderatorWaitingList.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");

            if(email != null && !email.isEmpty()){
                DModeratorDAO.removeDissmissedModerator(email);
                response.sendRedirect(request.getContextPath() + "/in-waiting-mod-servlet");
            }




        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}