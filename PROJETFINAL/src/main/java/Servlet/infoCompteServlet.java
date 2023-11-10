package Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/infoCompteServlet")
public class InfoCompteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("infoCompte.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //try {
            PrintWriter out= response.getWriter();
            HttpSession session = request.getSession();
            String email=(String) session.getAttribute("email");
            out.println(email);
            out.close();
            /*if(signUpDAO.isUniqueEmail(email)){
                Compte c=new Compte(email,password);
                signUpDAO.addCompte(c);

                session.setAttribute("email", email);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/login-success.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Cette adresse e-mail est déjà utilisée. Veuillez en choisir une autre.");
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    //}
}
}
