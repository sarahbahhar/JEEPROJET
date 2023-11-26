
package JEE.Servlet;

import DAO.ComptebancaireDAO;
import Model.Comptebancaire;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "addCardDuringOrderServlet", value = "/add-card-during-servlet")
public class addCardDuringOrderServlet extends HttpServlet {
    private ComptebancaireDAO bAccountDAO=new ComptebancaireDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(); // Retrieve the session
        String email = (String) ((HttpSession) session).getAttribute("email");
        String name = request.getParameter("nom");
        String numero = request.getParameter("numero");
        String expiration = request.getParameter("expiration");
        String cvvS = request.getParameter("cvv");
        Comptebancaire cB = new Comptebancaire();

        try {
            if(email!=null && name!=null && expiration!=null && cvvS!= null && numero!=null){
                cB.setEmail(email);
                cB.setNom(name);
                cB.setDate(expiration);
                cB.setNumero(numero);
                cB.setCvv(cvvS);
                bAccountDAO.addComptebancaire(cB);


            }
            List<Comptebancaire> cartesBancaires = ComptebancaireDAO.getListCBByEmail(email);
            request.setAttribute("cartesBancaires", cartesBancaires);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
            dispatcher.forward(request, response);

        }catch(Exception e){
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }

    }
}

