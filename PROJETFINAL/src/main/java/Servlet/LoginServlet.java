package Servlet;
import java.io.IOException;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import DAO.CompteDAO;
import DAO.CustomerDAO;
import DAO.ModeratorDAO;
import DAO.AdminDAO;
import DAO.InfoAccountDAO;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private CompteDAO loginDAO;


    public void init(){
        loginDAO=new CompteDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("signIn.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter out= response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        out.println(email);

        if (loginDAO.validate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email",email);

            if (AdminDAO.emailExists(email)) {
                session.setAttribute("role", 2); // 2 pour admin

            }else if (ModeratorDAO.emailExists(email)) {
                session.setAttribute("role", 1); // 1 pour moderateur
                int[] allPermissions = ModeratorDAO.getAllPermissionsByEmail(email);
                session.setAttribute("maxProductsPerLine",allPermissions[0]);
                session.setAttribute("canAddProduct",allPermissions[1] == 1);
                session.setAttribute("canDeleteProduct",allPermissions[2] == 1);


            }else if (CustomerDAO.emailExists(email)) {
                session.setAttribute("role", 0); // 0 pour client

            }
            String[] fullName = InfoAccountDAO.getFullNameByEmail(email);
            session.setAttribute("firstName",fullName[0]);
            session.setAttribute("lastName",fullName[1]);







            response.sendRedirect(request.getContextPath());

        } else {
            throw new Exception("Login not successful..");
        }
        out.close();
    }
}
