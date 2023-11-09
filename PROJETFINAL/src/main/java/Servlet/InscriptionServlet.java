package Servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import DAO.CompteDAO;
import Model.Compte;

@WebServlet("/inscription")
public class InscriptionServlet extends HttpServlet{
    private CompteDAO signUpDAO=new CompteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("signUp.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String password= request.getParameter("password");
            if(signUpDAO.isUniqueEmail(email)){
                Compte c=new Compte(email,password);
                signUpDAO.addCompte(c);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/login-success.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Cette adresse e-mail est déjà utilisée. Veuillez en choisir une autre.");
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
