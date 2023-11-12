package Servlet;
import java.io.IOException;
import Util.EmailSender;
import Model.Compte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import DAO.CompteDAO;

@WebServlet("/inscriptionServlet")
public class InscriptionServlet extends HttpServlet{
    private CompteDAO signUpDAO=new CompteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("WEB-INF/Vue/signUp.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String password= request.getParameter("password");
            String nom= request. getParameter("nom");
            String prenom=request.getParameter("prenom");
            if(signUpDAO.isUniqueEmail(email)){
                Compte c=new Compte();
                c.setEmail(email);
                c.setMotDePasse(password);
                signUpDAO.addCompte(c);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("nom", nom);
                session.setAttribute("prenom", prenom);
                //EmailSender emailSender=new EmailSender();
                //emailSender.sendMessage("rensimon@cy-tech.fr", email);
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/infoCompte.jsp");
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
