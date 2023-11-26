package JEE.Servlet;

import DAO.CompteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ResetPasswordServlet", value = "/reset-password-servlet")
public class ResetPasswordServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        CompteDAO.changePasswordByEmail(email,newPassword);

        response.sendRedirect(request.getContextPath());



    }
}
