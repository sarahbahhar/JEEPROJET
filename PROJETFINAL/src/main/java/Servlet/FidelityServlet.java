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
            if(usePoints.isEmpty()){
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
                dispatcher.forward(request, response);
            }
            else{
                session.setAttribute("total", (double) session.getAttribute("total") -10);
                Client c=new Client();
                Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
                c.setEmail(ic.getEmail());
                int pf=(int)session.getAttribute("pointFidelite");
                c.setPointsFidelite(pf-100);
                CustomerDAO.updateClient(ic.getEmail(), c);
                //out.println(email);
                //out.close();

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
                dispatcher.forward(request, response);
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}



