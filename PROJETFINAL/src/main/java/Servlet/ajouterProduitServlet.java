package Servlet;
import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;


import Model.Produit;
import DAO.ProduitDAO;

@WebServlet(name = "ajouterProduitServlet", value = "/ajouter-produit-servlet")
public class AjouterProduitServlet extends HttpServlet{
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

                Part filePart = request.getPart("image");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Valider que c'est bien une image
                if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".jpeg") &&
                        !fileName.toLowerCase().endsWith(".png") && !fileName.toLowerCase().endsWith(".gif")) {

                    response.sendRedirect(request.getContextPath() + "/erreur.jsp");
                    return;
                }

                // Enregistrer l'image dans le répertoire ./img
                try (InputStream fileContent = filePart.getInputStream();
                     OutputStream outputStream = new FileOutputStream("./img/" + fileName)) {
                    int read;
                    byte[] bytes = new byte[1024];
                    while ((read = fileContent.read(bytes)) != -1) {
                        outputStream.write(bytes, 0, read);
                    }
                } catch (IOException e) {
                    // Gérer les erreurs d'écriture du fichier, par exemple, rediriger vers une page d'erreur
                    response.sendRedirect(request.getContextPath() + "/erreur.jsp");
                    return;
                }

                Produit p = new Produit();
                p.setTitre(request.getParameter("titre"));
                p.setNomImage(fileName);
                p.setMiniDescription(request.getParameter("miniDescription"));
                p.setPrix(prix);
                p.setDescription(request.getParameter("description"));
                p.setStock(stock);
                p.setEmail(request.getParameter("email"));



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
