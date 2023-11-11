package Servlet;


import DAO.ProduitDAO;
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
        response.sendRedirect("moderatorList.jsp");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            if(email != null && !email.isEmpty()){
                ProduitDAO.removeProduct(email);

                response.sendRedirect(request.getContextPath() + "/product-servlet");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void deleteImageFile(String filePath) {


        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Image file deleted: " + filePath);
            } else {
                System.err.println("Failed to delete image file: " + filePath);
            }
        } else {
            System.err.println("Image file not found: " + filePath);
        }
    }
}

