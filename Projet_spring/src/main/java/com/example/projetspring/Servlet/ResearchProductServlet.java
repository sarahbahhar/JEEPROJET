
package com.example.projetspring.Servlet;

        import DAO.ProduitDAO;
        import Entity.Produit;
        import jakarta.servlet.RequestDispatcher;
        import jakarta.servlet.ServletException;
        import jakarta.servlet.annotation.WebServlet;
        import jakarta.servlet.http.HttpServlet;
        import jakarta.servlet.http.HttpServletRequest;
        import jakarta.servlet.http.HttpServletResponse;

        import java.io.IOException;
        import java.util.List;

        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "ResearchProductServlet", value = "/research-product-servlet")
public class ResearchProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitDAO produitDAO = new ProduitDAO();
        String titre = request.getParameter("query");
        List<Produit> listProduct = produitDAO.getListProductByTitre(titre);
        request.setAttribute("produits", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/researchProductResult.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
}
