package Servlet;
import java.io.IOException;
import java.util.Date;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import DAO.ComptebancaireDAO;

import Model.Comptebancaire;
import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyBankServlet")
public class VerifyBankServlet extends HttpServlet{
    private ComptebancaireDAO CBDAO;



    public void init(){
        CBDAO=new ComptebancaireDAO();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.sendRedirect("WEB-INF/Vue/payment.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            authenticateBank(request, response);



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    private void authenticateBank(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //PrintWriter out= response.getWriter();
        String numero = request.getParameter("numero");
        String cvv = request.getParameter("cvv");
        String date= request.getParameter("date");
        //out.println(email);


        if (CBDAO.validate(numero, cvv, date)) {
            //HttpSession session = request.getSession();
            //session.setAttribute("email",email);
            response.sendRedirect(request.getContextPath() +"/CreateCommandeServlet");
            //RequestDispatcher dispatcher = request.getRequestDispatcher("/CreateCommandeServlet");
            //dispatcher.forward(request, response);


        } else {
            throw new Exception("payment not successful..");
        }
        //out.close();
    }
}
