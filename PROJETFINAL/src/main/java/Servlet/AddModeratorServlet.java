package Servlet;

        import DAO.CompteDAO;
        import Model.Compte;
        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;

        import java.io.IOException;
        import java.util.List;

@WebServlet(name = "addModeratorServlet", value = "/add-moderator-servlet")
public class AddModeratorServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CompteDAO test = new CompteDAO();
        List<Compte> listCompte = test.getListCompte();
        request.setAttribute("comptes",listCompte);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/addModerator.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
