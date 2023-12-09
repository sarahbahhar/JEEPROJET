package Servlet;

import java.math.BigDecimal;

import DAO.ComptebancaireDAO;
import Model.Comptebancaire;
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
import java.util.List;

import Model.Client;
import DAO.CustomerDAO;
import DAO.PanierDAO;
import Model.Panier;
import DAO.InfoAccountDAO;
import Model.Infocompte;

@WebServlet("/FidelityServlet")
public class FidelityServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //PrintWriter out= response.getWriter();
            HttpSession session = request.getSession();
            String usePoints= request.getParameter("usePoints");
            Infocompte ic = (Infocompte) session.getAttribute("InfoCompte");
            String email = ic.getEmail();
            List<Comptebancaire> cartesBancaires = ComptebancaireDAO.getListCBByEmail(email);
            request.setAttribute("cartesBancaires", cartesBancaires);
            if(usePoints!=null && usePoints.equals("true")){
                session.setAttribute("total", (double) session.getAttribute("total") -10);
                Client c=new Client();

                c.setEmail(ic.getEmail());
                int pf=(int)session.getAttribute("pointFidelite");
                c.setPointsFidelite(pf-100);
                CustomerDAO.updateClient(ic.getEmail(), c);
                session.setAttribute("pointFidelite", pf-100);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
                dispatcher.forward(request, response);
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
                dispatcher.forward(request, response);
            }



        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}



