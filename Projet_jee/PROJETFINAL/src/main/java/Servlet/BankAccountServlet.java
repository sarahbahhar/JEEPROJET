package Servlet;

        import DAO.ComptebancaireDAO;
        import DAO.ModeratorDAO;
        import Model.Comptebancaire;
        import Model.Moderateur;
        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;
        import jakarta.servlet.http.HttpSession;

        import java.io.IOException;
        import java.util.List;

@WebServlet(name = "BankAccountServlet", value = "/bank-account-servlet")
public class BankAccountServlet extends HttpServlet {
    /**
     * get information form a get
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("email");
        ComptebancaireDAO test = new ComptebancaireDAO();
        List<Comptebancaire> listCB = test.getListCBByEmail(userEmail);
        request.setAttribute("accounts",listCB);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/bankAccountList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}