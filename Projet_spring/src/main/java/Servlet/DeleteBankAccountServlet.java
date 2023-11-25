package Servlet;

import DAO.ComptebancaireDAO;
import DAO.CustomerDAO;
import DAO.ModeratorDAO;
import Model.Client;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "DeleteBankAccountServlet", value = "/delete-bank-account-servlet")
public class DeleteBankAccountServlet extends HttpServlet {
    private ComptebancaireDAO CBDAO=new ComptebancaireDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String numero=request.getParameter("numero");
            if(email != null && !email.isEmpty()&&numero!=null&&!numero.isEmpty()){
                CBDAO.deleteComptebancaire(email,numero);
                response.sendRedirect(request.getContextPath() + "/bank-account-servlet");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
