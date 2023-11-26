package JEE.Servlet;

import DAO.ModeratorDAO;
import Model.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Moderateur;

@WebServlet(name = "moderatorServlet", value = "/moderator-servlet")
public class ModeratorServlet extends HttpServlet {
    private final ModeratorDAO moderatorDAO = new ModeratorDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Moderateur> listModerator = moderatorDAO.getListModerateur();

        Map<String, BigDecimal> averageRatings = new HashMap<>();
        for (Moderateur moderator : listModerator) {
            BigDecimal averageRating = moderatorDAO.getAverageRatingByEmail(moderator.getEmail());
            averageRatings.put(moderator.getEmail(), averageRating);
        }

        request.setAttribute("moderators", listModerator);
        request.setAttribute("averageRatings", averageRatings);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/moderatorList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}


