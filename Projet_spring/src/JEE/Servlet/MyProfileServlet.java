package JEE.Servlet;

import DAO.ModeratorDAO;
import Model.Infocompte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

@WebServlet(name = "MyProfileServlet", value = "/my-profile-servlet")
public class MyProfileServlet extends HttpServlet {
    private final ModeratorDAO moderatorDAO = new ModeratorDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
        String emailVendeur = ic.getEmail(); // Récupération de l'email depuis la requête GET

        BigDecimal averageRating = moderatorDAO.getAverageRatingByEmail(emailVendeur);

        request.setAttribute("averageRating", averageRating);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/myProfile.jsp");
        dispatcher.forward(request, response);
    }
}


