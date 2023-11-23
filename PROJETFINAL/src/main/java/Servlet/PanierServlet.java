package Servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import DAO.ProduitDAO;
import Model.Panier;
import Model.Produit;
import Model.Produitcommande;
import DAO.PanierDAO;

import Model.Produitpanier;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "PanierServlet", value = "/panier-servlet")
public class PanierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        ProduitDAO prduitDAO = new ProduitDAO();
        List<Produitpanier> panier = PanierDAO.getListProduitpanier(email);
        List<Produit> produitsDansPanier = new ArrayList<>();

        double total = 0.0;

        for (Produitpanier produitpanier : panier) {
            // Obtenez les détails du produit associé à Produitcommande depuis la base de données
            Produit produit = ProduitDAO.getProduitById(produitpanier.getProduitId());

            // Vérifiez si le produit existe avant de l'utiliser
            if (produit != null) {
                total += produit.getPrix().doubleValue() * produitpanier.getQuantite();

                produitsDansPanier.add(produit);
            }
        }
        Panier p=new Panier();
        BigDecimal zero= new BigDecimal(0);
        BigDecimal ttc=new BigDecimal(total);
        p.setTtc(ttc);
        p.setTva(zero);
        p.setHt(zero);
        p.setEmail(email);
        PanierDAO.updatePanier(email, p);


        request.setAttribute("panier", panier);
        request.setAttribute("total", total);
        request.setAttribute("produitsDansPanier", produitsDansPanier);
        HttpSession session = request.getSession();
        session.setAttribute("total",total);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/panier.jsp");
        try {

            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            int produitId = Integer.parseInt(request.getParameter("produit_id"));
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            String email = request.getParameter("email");



            PanierDAO.ajouterProduitAuPanier(email, produitId, quantite);

            response.sendRedirect(request.getContextPath()+"/product-details?type=pageProduit&produit_id="+produitId +produitId);

    }


}
