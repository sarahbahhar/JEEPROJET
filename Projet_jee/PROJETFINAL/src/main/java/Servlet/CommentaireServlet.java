package Servlet;

import DAO.CommentairesDAO;
import DAO.ProduitDAO;
import Model.Commentaires;
import Model.Infocompte;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "CommentaireServlet", value = "/commentaire-servlet")
public class CommentaireServlet extends HttpServlet {
    /**
     * get information form a post
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        String commentaire = request.getParameter("commentaire");
        int produitId = Integer.parseInt(request.getParameter("produit_id"));
        Produit produit = ProduitDAO.getProduitById(produitId);
        String emailVendeur = produit.getEmail();
        int rating = Integer.parseInt(request.getParameter("rating"));
        Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
        String emailAcheteur = ic.getEmail(); // Récupération de l'email de l'acheteur


        Commentaires newComment = new Commentaires();
        newComment.setIdProduit(Integer.parseInt(request.getParameter("produit_id")));
        newComment.setNote(rating);
        newComment.setEmailVendeur(emailVendeur);
        newComment.setCommentaire(commentaire);
        newComment.setEmail(emailAcheteur);

        // Enregistrement dans la base de données
        CommentairesDAO.addCommentaire(newComment);

        // Redirection vers la page produit ou un message de confirmation
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/productList.jsp");
        response.sendRedirect(request.getContextPath()+"/product-details?type=pageProduit&produit_id="+produitId); // changer type=page  produti et idproduit s'inspirer de produitCommande
    }
}

