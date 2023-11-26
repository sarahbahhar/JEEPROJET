package com.example.projetspring.Servlet;

import DAO.CommentairesDAO;
import DAO.ProduitDAO;
import Entity.Commentaires;
import Entity.Infocompte;
import Entity.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "CommentaireServlet", value = "/commentaire-servlet")
public class CommentaireServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession();
        String commentaire = request.getParameter("commentaire");
        int produitId = Integer.parseInt(request.getParameter("produit_id"));
        Produit produit = ProduitDAO.getProduitById(produitId);
        String emailVendeur = produit.getEmail();
        int rating = Integer.parseInt(request.getParameter("rating"));
        Infocompte ic=(Infocompte) session.getAttribute("InfoCompte");
        String emailAcheteur = ic.getEmail(); // Récupération de l'email de l'acheteur
         // Récupération de l'email de l'utilisateur connecté

        // Validation des données (vérification des plages, nettoyage des entrées, etc.)

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

