package Servlet;
import java.io.IOException;

import DAO.*;
import Model.Compte;
import DAO.TokenDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

@WebServlet("/inscriptionServlet")
public class InscriptionServlet extends HttpServlet{
    private CompteDAO signUpDAO=new CompteDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("WEB-INF/Vue/signUp.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email=request.getParameter("email");
            String password= request.getParameter("password");
            String nom= request. getParameter("nom");
            String prenom=request.getParameter("prenom");
            if(signUpDAO.isUniqueEmail(email)){
                /*Compte c=new Compte();
                c.setEmail(email);
                c.setAndHashMotDePasse(password);
                signUpDAO.addCompte(c);*/

                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("nom", nom);
                session.setAttribute("prenom", prenom);
                session.setAttribute("password", password);


                //EmailSender emailSender=new EmailSender();
                //emailSender.sendMessage("rensimon@cy-tech.fr", email);
                String htmlContent = getHtmlContentFromJsp("/mail/mailOfWelcome.jsp", request, response);

                sendEmailWithHTML(email,"Bienvenue sur Divan",htmlContent);
                //response.sendRedirect("MailServlet");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/infoCompte.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("failEmailExist",true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vue/signIn2.jsp");
                dispatcher.forward(request, response);

            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
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

