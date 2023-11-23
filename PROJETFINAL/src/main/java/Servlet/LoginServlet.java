package Servlet;
import java.io.IOException;
import java.io.PrintWriter;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.CompteDAO;
import DAO.CustomerDAO;
import DAO.ModeratorDAO;
import DAO.AdminDAO;
import DAO.InfoAccountDAO;

import Model.Infocompte;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
    private CompteDAO loginDAO;
    private InfoAccountDAO infocompteDAO;


    public void init(){
        loginDAO=new CompteDAO();
        infocompteDAO=new InfoAccountDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("WEB-INF/Vue/signIn.jsp");
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
        response.setCharacterEncoding("UTF-8");
        //PrintWriter out= response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        //System.out.println(password);


        if (CompteDAO.validate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email",email);
            session.setAttribute("demandeModerateur",DAO.DemandeModerateurDAO.isEmailInModeratorRequests(email));

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

            Infocompte ic=infocompteDAO.getInfoCompte(email);

            session.setAttribute("InfoCompte", ic);
            /*session.setAttribute("email", email);
            session.setAttribute("prenom", ic.getPrenom());
            session.setAttribute("nom", ic.getNom());
            session.setAttribute("date", ic.getDateNaissance());
            session.setAttribute("telephone", ic.getTelephone());
            session.setAttribute("adresse", ic.getAdresse());
            session.setAttribute("ville", ic.getVille());
            session.setAttribute("code", ic.getCodePostal());
            session.setAttribute("pays", ic.getPays());*/
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/Vue/home.jsp");
            //dispatcher.forward(request, response);
        } else {
            PrintWriter out=response.getWriter();

            out.println("Mot de passe incorrect. Veuillez réessayer.");
            response.setHeader("Refresh", "3; URL=redirect-servlet?path=signIn.jsp");
            out.close();
            throw new Exception("Login not successful..");
        }
        //out.close();
    }
}
