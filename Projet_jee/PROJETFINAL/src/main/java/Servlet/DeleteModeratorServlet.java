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

    /**
     * get information form a post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
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