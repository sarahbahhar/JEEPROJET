
package Servlet;

import DAO.BankAccountDAO;
import Model.Comptebancaire;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet(name = "NewBankAccountServlet", value = "/new-bank-account-servlet")
public class NewBankAccountServlet extends HttpServlet {
    private BankAccountDAO bAccountDAO=new BankAccountDAO();
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
                    bAccountDAO.addBankAccount(cB);
                    response.sendRedirect(request.getContextPath() + "/product-servlet");
            }

            }catch(Exception e){
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
}

