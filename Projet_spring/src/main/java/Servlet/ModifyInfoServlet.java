package Servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.io.IOException;


import DAO.InfoAccountDAO;
import Model.Infocompte;


@WebServlet("/ModifyInfoServlet")

public class ModifyInfoServlet extends HttpServlet {
    private InfoAccountDAO infocompteDAO=new InfoAccountDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("WEB-INF/Vue/myProfile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //PrintWriter out= response.getWriter();
            HttpSession session = request.getSession();
            Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
            String nom= request.getParameter("nom");
            String prenom= request.getParameter("prenom");
            String email=ic.getEmail();
            String dateString= request.getParameter("date");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String telephone=request.getParameter("telephone");
            String adresse=request.getParameter("adresse");
            String ville=request.getParameter("ville");
            Integer codePostal= Integer.parseInt(request.getParameter("codePostal"));
            String pays=request.getParameter("pays");


            //out.println(email);
            //out.close();

            Infocompte updated=new Infocompte();
            updated.setEmail(email);
            updated.setDateNaissance((java.sql.Date) sqlDate);
            updated.setTelephone(telephone);
            updated.setAdresse(adresse);
            updated.setVille(ville);
            updated.setCodePostal(codePostal);
            updated.setPays(pays);
            updated.setNom(nom);
            updated.setPrenom(prenom);
            infocompteDAO.updateInfoCompte(email,updated);
            session.setAttribute("InfoCompte", updated);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/myProfile.jsp");
            dispatcher.forward(request, response);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
