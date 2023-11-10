package Servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.math.BigDecimal;

import DAO.ProduitDAO;

@WebServlet(name = "ajouterProduitServlet", value = "/ajouter-produit-servlet")
public class ajouterProduitServlet extends HttpServlet{
    private ProduitDAO ProduitDAO=new ProduitDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("addProduct.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String titre=request.getParameter("titre");
            String nomImage= request.getParameter("nomImage");
            String miniDescription=request.getParameter("miniDescription");
            String description=request.getParameter("description");
            Integer stock= Integer.parseInt(request.getParameter("stock"));
            String email= request.getParameter("email");
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
                p.setStock(stock);
                p.setEmail(request.getParameter("email"));

// Vous avez maintenant un objet Produit (p) avec tous les attributs remplis.

                ProduitDAO.addProduct(p);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/produitList.jsp");
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
