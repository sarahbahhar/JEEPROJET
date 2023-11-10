package Servlet;

import Model.Compte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


import DAO.InfoCompteDAO;
import Model.Infocompte;

@WebServlet("/infoCompteServlet")
public class infoCompteServlet extends HttpServlet {
    private InfoCompteDAO infocompteDAO;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("infoCompte.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out= response.getWriter();
            HttpSession session = request.getSession();

            String email=(String) session.getAttribute("email");
            String dateString= request.getParameter("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            String telephone=request.getParameter("telephone");
            String adresse=request.getParameter("adresse");
            String ville=request.getParameter("ville");
            Integer codePostal= Integer.parseInt(request.getParameter("codePostal"));
            String pays=request.getParameter("pays");
            out.println(email);
            out.close();
            Infocompte infocompte=new Infocompte();
            //infocompte.setDateNaissance(date);
            infocompte.setTelephone(telephone);
            infocompte.setAdresse(adresse);
            infocompte.setVille(ville);
            infocompte.setCodePostal(codePostal);
            infocompte.setPays(pays);
            infocompteDAO.addInfoCompte(infocompte);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/login-success.jsp");
            dispatcher.forward(request, response);

            /*if(signUpDAO.isUniqueEmail(email)){
                Compte c=new Compte(email,password);
                signUpDAO.addCompte(c);

                session.setAttribute("email", email);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/login-success.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Cette adresse e-mail est déjà utilisée. Veuillez en choisir une autre.");
            }*/



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

