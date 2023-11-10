package Servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import org.hibernate.Session;

import Model.Produit;
import DAO.ProduitDAO;

@WebServlet(name = "AddProductServlet", value = "/add-product-servlet")
public class AddProductServlet extends HttpServlet{
    private ProduitDAO ProduitDAO=new ProduitDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("addProduct.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {


            String prixParameter = request.getParameter("prix");
            BigDecimal prix = null;

            if (prixParameter != null && !prixParameter.isEmpty()) {
                try {
                    prix = new BigDecimal(prixParameter);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur si la chaîne n'est pas un nombre valide
                    // Vous pouvez loguer l'erreur ou prendre une autre action appropriée.
                }
            }


            if(true){
                // Créez d'abord un objet Produit en utilisant le constructeur vide
                Produit p = new Produit();
                p.setTitre(request.getParameter("titre"));
                p.setNomImage(request.getParameter("nomImage"));
                p.setMiniDescription(request.getParameter("miniDescription"));
                p.setPrix(prix);
                p.setDescription(request.getParameter("description"));
                p.setStock(Integer.parseInt(request.getParameter("stock")));
                p.setEmail(request.getParameter("email"));

// Vous avez maintenant un objet Produit (p) avec tous les attributs remplis.

                ProduitDAO.addProduct(p);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/productList.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Pb");
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
