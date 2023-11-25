package Servlet;


import DAO.ModeratorDAO;
import Model.Moderateur;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "EditModeratorServlet", value = "/edit-moderator-servlet")
public class EditModeratorServlet extends HttpServlet {

    private ModeratorDAO ModDAO=new ModeratorDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Moderateur> listModerator = ModDAO.getListModerateur();
        request.setAttribute("moderators",listModerator);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/editModerator.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String addProdParam = request.getParameter("addP");
            String deleteProdParam=request.getParameter("deleteP");
            String maxProdParam=request.getParameter("maxProd");
            byte addProd=Byte.parseByte(addProdParam);
            byte deleteProd=Byte.parseByte(deleteProdParam);
            int maxProd=Integer.parseInt(maxProdParam);
            Moderateur updateMod=new Moderateur();
            updateMod.setEmail(email);
            updateMod.setPeutAjouterProduit(addProd);
            updateMod.setPeutSupprimerProduit(deleteProd);
            updateMod.setMaxProduitsLigne(maxProd);
            ModDAO.updateModerator(updateMod);
            response.sendRedirect(request.getContextPath() + "/moderator-servlet");

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}