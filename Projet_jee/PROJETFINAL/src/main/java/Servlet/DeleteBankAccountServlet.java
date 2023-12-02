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
            String id=request.getParameter("id");
            if(email != null && !email.isEmpty()&&id!=null&&!id.isEmpty()){
                CBDAO.deleteComptebancaire(email,id);
                response.sendRedirect(request.getContextPath() + "/bank-account-servlet");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}
