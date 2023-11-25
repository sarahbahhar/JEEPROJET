package Servlet;
import java.io.*;

import DAO.ModeratorDAO;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.Part;
import org.hibernate.Session;


import Model.Produit;
import DAO.ProduitDAO;
import jakarta.servlet.http.Part;

@WebServlet(name = "BannirModeratorServlet", value = "/bannir-moderator-servlet")
public class BannirModeratorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String motifCourt = request.getParameter("motifCourt");
        String motifLong = request.getParameter("motifLong");
        String dateFinBan = request.getParameter("dateFinBan");


        ModeratorDAO.bannirByEmail(email, motifCourt, motifLong,dateFinBan);

        // Redirection ou gestion de la réponse après le bannissement

        response.sendRedirect(request.getContextPath()+"/moderator-servlet"); // changer type=page  produti et idproduit s'inspirer de produitCommande

    }
}
