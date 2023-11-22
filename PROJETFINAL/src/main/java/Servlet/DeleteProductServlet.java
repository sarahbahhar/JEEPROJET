package Servlet;


import DAO.ProduitDAO;
import Model.Produit;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.File;
import java.io.IOException;
@WebServlet(name = "DeleteProductServlet", value = "/delete-product-servlet")
public class DeleteProductServlet extends HttpServlet {
    public static final String PATH_IMAGE = "/src/main/webapp/img/";

    private ProduitDAO ProDAO=new ProduitDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        try {
            String idStr=request.getParameter("id");

            Integer id = Integer.parseInt(idStr);


            if (id != null){
                deleteImageFile(ProduitDAO.getProduitById(id).getNomImage());

                ProduitDAO.removeProductById(id);


            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/my-product-list-servlet?email="+email); // changer type=page  produti et idproduit s'inspirer de produitCommande

    }

    private void deleteImageFile(String fileName) {

        // Obtenez le chemin réel à partir du chemin relatif
        String absolutePath = getServletContext().getRealPath("/") + "../../" + PATH_IMAGE;

        // Assurez-vous que le répertoire existe, sinon créez-le
        File imageDir = new File(absolutePath);


        // Maintenant, utilisez le chemin absolu pour écrire le fichier
        File file = new File(imageDir, fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Image file deleted: " + fileName);
            } else {
                System.err.println("Failed to delete image file: " + fileName);
            }
        } else {
            System.err.println("Image file not found: " + fileName);
        }
    }


}

