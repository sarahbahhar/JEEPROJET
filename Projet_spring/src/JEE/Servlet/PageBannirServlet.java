
package JEE.Servlet;

import DAO.ComptebancaireDAO;
import Model.Comptebancaire;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


    @WebServlet(name = "PageBannirServlet", value = "/page-bannir-servlet")
    public class PageBannirServlet extends HttpServlet {

        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

            String email =request.getParameter("email");


            request.setAttribute("emailModerateur",email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/bannir.jsp");

            dispatcher.forward(request, response);

        }
    }
