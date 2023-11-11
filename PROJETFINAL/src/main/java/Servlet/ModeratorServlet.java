package Servlet;

import DAO.ModeratorDAO;
import Model.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "moderatorServlet", value = "/moderator-servlet")
public class ModeratorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModeratorDAO test = new ModeratorDAO();
        List<Moderateur> listModerator = test.getListModerateur();
        request.setAttribute("moderators",listModerator);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/moderatorList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
