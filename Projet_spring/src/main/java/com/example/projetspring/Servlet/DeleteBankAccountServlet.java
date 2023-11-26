package com.example.projetspring.Servlet;

import DAO.ComptebancaireDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@WebServlet(name = "DeleteBankAccountServlet", value = "/delete-bank-account-servlet")
public class DeleteBankAccountServlet extends HttpServlet {
    private ComptebancaireDAO CBDAO=new ComptebancaireDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String id=request.getParameter("id");
            if(email != null && !email.isEmpty()&&id!=null&&!id.isEmpty()){
                CBDAO.deleteComptebancaire(email,id);
                response.sendRedirect(request.getContextPath() + "/bank-account-servlet");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }
}
