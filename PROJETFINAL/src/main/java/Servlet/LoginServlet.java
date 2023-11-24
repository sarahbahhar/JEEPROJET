package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;


import Model.Moderateur;
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
        response.sendRedirect("WEB-INF/Vue/signIn2.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticate(request, response);

        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }



    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        response.setCharacterEncoding("UTF-8");
        //PrintWriter out= response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isBan=false;
        //System.out.println(password);


        if (CompteDAO.validate(email, password)) {
            HttpSession session = request.getSession();
            session.setAttribute("email",email);
            session.setAttribute("demandeModerateur",DAO.DemandeModerateurDAO.isEmailInModeratorRequests(email));

            if (AdminDAO.emailExists(email)) {
                session.setAttribute("role", 2); // 2 pour admin

            }else if (ModeratorDAO.emailExists(email)) {
                ModeratorDAO.unbanByEmail(email);
                session.setAttribute("role", 1); // 1 pour moderateur

                Moderateur m = ModeratorDAO.getModeratorByEmail(email);

                session.setAttribute("maxProductsPerLine",m.getMaxProduitsLigne());
                session.setAttribute("nbBannissement",m.getNbBannissement());
                session.setAttribute("motifCourtBannissement",m.getMotifCourtBannissement());
                session.setAttribute("motifLongBanissement",m.getMotifLongBanissement());
                session.setAttribute("dateBanni",m.getDateBanni());

                if(m.getDateBanni()!=null){
                    session.setAttribute("canAddProduct", false);
                    session.setAttribute("canDeleteProduct", false);
                    isBan=true;

                }else {
                    session.setAttribute("canAddProduct", m.getPeutAjouterProduit()==1 );
                    session.setAttribute("canDeleteProduct", m.getPeutSupprimerProduit()==1);
                }



            }else if (CustomerDAO.emailExists(email)) {
                session.setAttribute("role", 0); // 0 pour client

            }
            String[] fullName = InfoAccountDAO.getFullNameByEmail(email);
            session.setAttribute("firstName",fullName[0]);
            session.setAttribute("lastName",fullName[1]);

            if (isBan) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/yourAreBan.jsp");
                dispatcher.forward(request, response);

            }else{
                response.sendRedirect(request.getContextPath());
            }



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
            request.setAttribute("failLogin",true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/signIn2.jsp");
            dispatcher.forward(request, response);



        }
        //out.close();
    }
}
