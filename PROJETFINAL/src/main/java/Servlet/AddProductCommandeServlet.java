package Servlet;

import DAO.ProduitDAO; //import temporaire
import Model.Produit;
import DAO.ProduitCommandeDAO;
import Model.Produitcommande;
import DAO.PanierDAO;
import Model.Produitpanier;
import Model.Infocompte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/AddProductCommandeServlet")
public class AddProductCommandeServlet extends HttpServlet {
    private PanierDAO panierDAO;
    private ProduitCommandeDAO produitCommandeDAO;



    public void init(){
        produitCommandeDAO =new ProduitCommandeDAO();
        panierDAO=new PanierDAO();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("WEB-INF/Vue/payment.jsp");
        try {
            init();
            HttpSession session = request.getSession();

            Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
            String email=ic.getEmail();
            double totalDouble = (double) session.getAttribute("total");
            BigDecimal totalBigDecimal = new BigDecimal(totalDouble);
            LocalDate currentDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
            int nCommande= (int) request.getAttribute("nCommande");
            List<Produitpanier> list=PanierDAO.getListProduitpanier(email);
            Produitcommande pc= new Produitcommande();



            for( Produitpanier pp : list){
                //appel temporaire a corriger
                Produit p=ProduitDAO.getProduitById(pp.getProduitId());
                pc.setCommandeNumero(nCommande);
                pc.setQuantite(pp.getQuantite());
                pc.setEmailVendeur(pp.getEmail());
                pc.setTitre(p.getTitre());
                pc.setPrix(p.getPrix());
                produitCommandeDAO.insertProduitCommande(pc);
                p.setStock(p.getStock()-pc.getQuantite());//update the stock of the product
                ProduitDAO.updateProduct(p);

            }
            PanierDAO.resetPanier(email);
            PanierDAO.removeProduitPanier(email);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/confirmationCommande.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }




}

