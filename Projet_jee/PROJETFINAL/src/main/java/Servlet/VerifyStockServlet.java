package Servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import DAO.ComptebancaireDAO;
import DAO.CustomerDAO;
import DAO.PanierDAO;
import DAO.ProduitDAO;
import Model.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyStockServlet")
public class VerifyStockServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {


            HttpSession session = request.getSession();
            Infocompte ic = (Infocompte) session.getAttribute("InfoCompte");
            String email = ic.getEmail();
            List<Comptebancaire> cartesBancaires = ComptebancaireDAO.getListCBByEmail(email);
            request.setAttribute("cartesBancaires", cartesBancaires);

            List<Produitpanier> list = PanierDAO.getListProduitpanier(email);
            for (Produitpanier pp : list) {
                Produit p = ProduitDAO.getProduitById(pp.getProduitId());
                if (p.getStock() - pp.getQuantite() < 0) {
                    response.sendRedirect(request.getContextPath() + "/error");
                    return;
                }
            }

            Client c= CustomerDAO.getClient(email);
            int pointFidelite=c.getPointsFidelite();
            session.setAttribute("pointFidelite", pointFidelite);
            if(pointFidelite>=100 && (double) session.getAttribute("total")>10){
                request.setAttribute("totalReduit", (double) session.getAttribute("total")-10);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/fidelite.jsp");
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
