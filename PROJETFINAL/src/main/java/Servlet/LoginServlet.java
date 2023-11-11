package Servlet;
import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.CompteDAO;
import DAO.InfoAccountDAO;
import Model.Infocompte;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private CompteDAO loginDAO;
    private InfoAccountDAO infocompteDAO;

    public void init(){
        loginDAO=new CompteDAO();
        infocompteDAO=new InfoAccountDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signIn.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //PrintWriter out= response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //out.println(email);


        if (loginDAO.validate(email, password)) {
            Infocompte ic=infocompteDAO.getInfoCompte(email);
            HttpSession session= request.getSession();

            session.setAttribute("InfoCompte", ic);
            /*session.setAttribute("email", email);
            session.setAttribute("prenom", ic.getPrenom());
            session.setAttribute("nom", ic.getNom());
            session.setAttribute("date", ic.getDateNaissance());
            session.setAttribute("telephone", ic.getTelephone());
            session.setAttribute("adresse", ic.getAdresse());
            session.setAttribute("ville", ic.getVille());
            session.setAttribute("code", ic.getCodePostal());
            session.setAttribute("pays", ic.getPays());*/
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/home.jsp");
            dispatcher.forward(request, response);
        } else {
            throw new Exception("Login not successful..");
        }
        //out.close();
    }
}
