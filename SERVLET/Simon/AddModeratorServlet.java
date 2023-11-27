package Servlet;

        import DAO.CustomerDAO;
        import DAO.DemandeModerateurDAO;
        import DAO.ModeratorDAO;
        import Model.Moderateur;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;

        import java.io.IOException;

@WebServlet(name = "addModeratorServlet", value = "/add-moderator-servlet")
public class AddModeratorServlet extends HttpServlet {
    private DemandeModerateurDAO DModeratorDAO=new DemandeModerateurDAO();
    private ModeratorDAO ModDAO=new ModeratorDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("moderatorWaitingList.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");

            if(email != null && !email.isEmpty()){
                DModeratorDAO.removeDissmissedModerator(email);
                Moderateur m=new Moderateur();
                m.setEmail(email);
                m.setMaxProduitsLigne(10);
                m.setPeutAjouterProduit((byte) 1);
                m.setPeutSupprimerProduit((byte) 1);
                ModDAO.addModerator(m);
                response.sendRedirect(request.getContextPath() + "/moderator-servlet");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}
