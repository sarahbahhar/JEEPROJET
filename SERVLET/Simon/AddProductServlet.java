package Servlet;
import java.io.*;

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
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.Part;
import org.hibernate.Session;


import Model.Produit;
import DAO.ProduitDAO;
import jakarta.servlet.http.Part;

@WebServlet(name = "AddProductServlet", value = "/add-product-servlet")
@MultipartConfig(
        fileSizeThreshold = 10240, // La limite de taille après laquelle les fichiers seront écrits sur disque
        maxFileSize = 1024 * 1024 * 5, // La taille maximale d'un fichier
        maxRequestSize = 1024 * 1024 * 5 * 5 // La taille maximale de la requête (incluant tous les fichiers)
)

public class AddProductServlet extends HttpServlet {

    public static final int SIZE_BUFFER = 10240;
    public static final String PATH_IMAGE = "../../src/main/webapp/img/";

    private ProduitDAO ProduitDAO = new ProduitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/addProduct.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Produit p = new Produit();



            String priceParameter = request.getParameter("price");
            BigDecimal price = null;

            if (priceParameter != null && !priceParameter.isEmpty()) {
                try {
                    price = new BigDecimal(priceParameter);
                } catch (NumberFormatException e) {
                    // Gérer l'erreur si la chaîne n'est pas un nombre valide
                    // Vous pouvez loguer l'erreur ou prendre une autre action appropriée.
                }
            }

            Part filePart = request.getPart("image");
            String fileName = getNameFile(filePart);

            // Si on a bien un fichier
            if (fileName != null && !fileName.isEmpty()) {

                // On écrit définitivement le fichier sur le disque
                writeFile(filePart, fileName, PATH_IMAGE);

                writeFile(filePart, fileName, "img/");
            }
            Part filePart2 = request.getPart("image2");
            String fileName2 = getNameFile(filePart2);
            if (fileName2 != null && !fileName2.isEmpty()) {

                // On écrit définitivement le fichier sur le disque
                writeFile(filePart, fileName2, PATH_IMAGE);

                writeFile(filePart, fileName2, "img/");
            }
            if (true) {
                // Créez d'abord un objet Produit en utilisant le constructeur vide

                p.setTitre(request.getParameter("titre"));
                p.setNomImage(fileName);
                p.setNomImage2(fileName2);
                p.setMiniDescription(request.getParameter("miniDescription"));
                p.setCategorie(request.getParameter("categorie"));
                p.setPrix(price);
                p.setDescription(request.getParameter("description"));
                p.setStock(Integer.parseInt(request.getParameter("stock")));
                p.setEmail(request.getParameter("email"));


// Vous avez maintenant un objet Produit (p) avec tous les attributs remplis.

                ProduitDAO.addProduct(p);


            } else {
                request.setAttribute("error", "Pb");
            }


        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();

        }
        String email = request.getParameter("email");
        response.sendRedirect(request.getContextPath()+"/my-product-list-servlet?email="+email); // changer type=page  produti et idproduit s'inspirer de produitCommande



    }

    private static String getNameFile(Part part) {
        for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
            if (contentDisposition.trim().startsWith("filename")) {
                String originalFileName = contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
                // Générer un identifiant unique
                String uniqueID = UUID.randomUUID().toString();
                return uniqueID + "_" + originalFileName;
            }
        }
        return null;
    }

    private void writeFile(Part part, String fileName, String path) throws IOException {
            // Obtenez le chemin réel à partir du chemin relatif
            String absolutePath = getServletContext().getRealPath("/")  + path;

            // Assurez-vous que le répertoire existe, sinon créez-le
            File imageDir = new File(absolutePath);
            if (!imageDir.exists()) {
                imageDir.mkdirs();
            }

            // Maintenant, utilisez le chemin absolu pour écrire le fichier
            File outputFile = new File(imageDir, fileName);
            try (BufferedOutputStream exit = new BufferedOutputStream(new FileOutputStream(outputFile), SIZE_BUFFER)) {
                BufferedInputStream entrance = new BufferedInputStream(part.getInputStream(), SIZE_BUFFER);

                byte[] buffer = new byte[SIZE_BUFFER];
                int length;
                while ((length = entrance.read(buffer)) > 0) {
                    exit.write(buffer, 0, length);
                }
            }
        }


}

