package Servlet;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import DAO.PanierDAO;
import DAO.ProduitDAO;
import Model.Infocompte;
import Model.Produit;
import Model.Produitpanier;
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
        //response.sendRedirect("WEB-INF/Vue/payment.jsp");


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Infocompte ic = (Infocompte) session.getAttribute("InfoCompte");
            String email = ic.getEmail();
            List<Produitpanier> list = PanierDAO.getListProduitpanier(email);
            for (Produitpanier pp : list) {
                Produit p = ProduitDAO.getProduitById(pp.getProduitId());
                if (p.getStock() - pp.getQuantite() < 0) {
                    response.sendRedirect(request.getContextPath() + "/error");
                    return;
                }
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/payment.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
