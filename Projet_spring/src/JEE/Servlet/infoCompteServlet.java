package JEE.Servlet;
import java.math.BigDecimal;

import DAO.*;
import Model.Compte;
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
import Model.Client;
import Model.Panier;
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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = dateFormat.parse(dateString);
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String telephone=request.getParameter("telephone");
            String adresse=request.getParameter("adresse");
            String ville=request.getParameter("ville");
            Integer codePostal= Integer.parseInt(request.getParameter("codePostal"));
            String pays=request.getParameter("pays");


            //out.println(email);
            //out.close();
            Compte compte=new Compte();
            compte.setEmail(email);
            compte.setAndHashMotDePasse((String) session.getAttribute("password"));
            session.removeAttribute("password");
            CompteDAO.addCompte(compte);//create Compte in DB
            TokenDAO.addToken(email);
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
            infocompteDAO.addInfoCompte(infocompte);// create infocomte in DB
            Panier p=new Panier();
            BigDecimal zero=new BigDecimal(0);
            p.setHt(zero);
            p.setTtc(zero);
            p.setTva(zero);
            p.setEmail(email);
            PanierDAO.createPanier(p);// create cart in DB
            Client c=new Client();
            c.setEmail(email);
            c.setPointsFidelite(0);
            CustomerDAO.addCustomer(c);//create Customer account in DB
            //session.setAttribute("InfoCompte", infocompte);
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);


        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}

