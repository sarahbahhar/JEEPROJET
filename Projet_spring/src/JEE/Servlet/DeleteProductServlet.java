package JEE.Servlet;


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
                ProduitDAO.removeProductById(getServletContext().getRealPath("/"),ProduitDAO.getProduitById(id).getNomImage(),ProduitDAO.getProduitById(id).getNomImage2(),id);




            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath()+"/my-product-list-servlet?email="+email); // changer type=page  produti et idproduit s'inspirer de produitCommande

    }




}

