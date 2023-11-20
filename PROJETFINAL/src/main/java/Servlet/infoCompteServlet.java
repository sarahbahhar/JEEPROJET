package Servlet;
import java.math.BigDecimal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.io.IOException;

import DAO.PanierDAO;
import Model.Panier;
import DAO.InfoAccountDAO;
import Model.Infocompte;

@WebServlet("/infoCompteServlet")
public class infoCompteServlet extends HttpServlet {
    private InfoAccountDAO infocompteDAO=new InfoAccountDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.sendRedirect("/WEB-INF/Vue/infoCompte.jsp");
        //String email = (String) request.getAttribute("email");
        //String nom = (String) request.getAttribute("nom");
        //String prenom = (String) request.getAttribute("prenom");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //PrintWriter out= response.getWriter();
            HttpSession session = request.getSession();
            String email=(String) session.getAttribute("email");
            String nom=(String) session.getAttribute("nom");
            String prenom=(String) session.getAttribute("prenom");

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
            Infocompte infocompte=new Infocompte();
            infocompte.setEmail(email);
            infocompte.setDateNaissance((java.sql.Date) sqlDate);
            infocompte.setTelephone(telephone);
            infocompte.setAdresse(adresse);
            infocompte.setVille(ville);
            infocompte.setCodePostal(codePostal);
            infocompte.setPays(pays);
            infocompte.setNom(nom);
            infocompte.setPrenom(prenom);
            infocompteDAO.addInfoCompte(infocompte);
            Panier p=new Panier();
            BigDecimal zero=new BigDecimal(0);
            p.setHt(zero);
            p.setTtc(zero);
            p.setTva(zero);
            p.setEmail(email);
            PanierDAO.createPanier(p);

            //session.setAttribute("InfoCompte", infocompte);
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

