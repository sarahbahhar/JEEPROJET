package Servlet;

import DAO.CompteDAO;
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



@WebServlet("/PasswordForgetServlet")
public class PasswordForgetServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer le token depuis la requête
        String token = request.getParameter("resetToken");
        String email = request.getParameter("email");

        // Récupérer le token depuis la session
        String sessionToken = TokenDAO.getTokenValueByEmail(email);

        if (token.equals(sessionToken)) { // verifier le token
            request.setAttribute("email", email);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/resetPassword.jsp");
            dispatcher.forward(request, response);
        } else {
            // Si le token n'est pas valide, afficher un message d'erreur ou rediriger vers une page d'erreur.
            response.sendRedirect("errorPage.jsp"); // Remplacer par votre page d'erreur.
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            if(DAO.CompteDAO.isUniqueEmail(email))
            {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/mailSend.jsp");

                dispatcher.forward(request, response);

            }else {

                String resetToken = TokenDAO.generateResetToken();
                TokenDAO.changeTokenByEmail(email, resetToken);


                String resetLink = "http://localhost:8080/" + request.getContextPath() + "/PasswordForgetServlet?resetToken=" + resetToken + "&email=" + email;


                request.setAttribute("resetLink", resetLink);

                String htmlContent = getHtmlContentFromJsp("/mail/mailPasswordForget.jsp", request, response);

                sendEmailWithHTML(email, "Réinitialisation du mot de passe", htmlContent);

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/mailSend.jsp");

                dispatcher.forward(request, response);
            }


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
