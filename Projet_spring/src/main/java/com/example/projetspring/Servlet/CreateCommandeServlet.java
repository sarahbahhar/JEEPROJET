package com.example.projetspring.Servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.CommandeDAO;
import Entity.Commande;
import Entity.Infocompte;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet("/CreateCommandeServlet")
public class CreateCommandeServlet  extends HttpServlet{
    private CommandeDAO commandeDAO;



    public void init(){
        commandeDAO=new CommandeDAO();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("WEB-INF/Vue/payment.jsp");
        try {
            HttpSession session = request.getSession();

            Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
            String email=ic.getEmail();
            double totalDouble = (double) session.getAttribute("total");
            BigDecimal totalBigDecimal = new BigDecimal(totalDouble);
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            Commande commande= new Commande();
            commande.setEmail(email);
            commande.setTotal(totalBigDecimal);
            commande.setDateDePaiement(sqlDate);
            commandeDAO.addCommande(commande);
            int nCommande=commandeDAO.getLastCommandeIdByEmail(email);

            request.setAttribute("nCommande", nCommande);
            /*PrintWriter out=response.getWriter();
            out.println(nCommande);
            out.close();*/

            RequestDispatcher dispatcher = request.getRequestDispatcher("AddProductCommandeServlet");///WEB-INF/Vue/confirmationCommande.jsp
            dispatcher.forward(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }




}

