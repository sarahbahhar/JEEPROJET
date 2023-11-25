package Servlet;

import DAO.AdminDAO;
import DAO.TokenDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;


import DAO.TokenDAO;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;



@WebServlet(name = "contactServlet", value = "/contact-servlet")
public class contactServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String emailCompte = request.getParameter("emailCompte");
            String emailAdmin = AdminDAO.getAdminEmail();
            String message = request.getParameter("message");



            request.setAttribute("message",message);
            request.setAttribute("emailCompte",emailCompte);

            String htmlContent = getHtmlContentFromJsp("/mail/mailContact.jsp", request, response);

            sendEmailWithHTML(emailAdmin,"Contact",htmlContent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/mailSendContact.jsp");

            dispatcher.forward(request, response);


        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
            e.printStackTrace();
        }
    }



    void sendEmailWithHTML(String recipientEmail,String subject, String content) throws ServletException {
        final String username = "projetjee.cytech@gmail.com";
        final String myPassword = "idgz udpj wywr dfpn ";//code d'application

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Créer une session de messagerie avec les propriétés configurées
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, myPassword);
            }
        });

        try {
            jakarta.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);

            message.setContent(content, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi de l'e-mail de confirmation", e);
        }
    }
    private String getHtmlContentFromJsp(String filePath, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringWriter stringWriter = new StringWriter();
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(filePath);

        dispatcher.include(request, new HttpServletResponseWrapper(response) {
            public PrintWriter getWriter() {
                return new PrintWriter(stringWriter);
            }
        });

        return stringWriter.toString();
    }
}
