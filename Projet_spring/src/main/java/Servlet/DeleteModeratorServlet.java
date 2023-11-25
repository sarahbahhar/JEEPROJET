package Servlet;

import DAO.CustomerDAO;
import DAO.DemandeModerateurDAO;
import DAO.ModeratorDAO;
import Model.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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
            if(email != null && !email.isEmpty()){
                ModeratorDAO.removeModerator(email);
                Client c =new Client();
                c.setEmail(email);
                CustomerDAO.addCustomer(c);
                response.sendRedirect(request.getContextPath() + "/moderator-servlet");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}