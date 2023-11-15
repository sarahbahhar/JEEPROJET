package Servlet;
import java.io.IOException;

import DAO.AdminDAO;
import DAO.CustomerDAO;
import DAO.ModeratorDAO;
import Model.Compte;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import DAO.CompteDAO;

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
                Compte c=new Compte();
                c.setEmail(email);
                c.setMotDePasse(password);
                signUpDAO.addCompte(c);
                HttpSession session = request.getSession();
                session.setAttribute("email", email);
                session.setAttribute("nom", nom);
                session.setAttribute("prenom", prenom);



                //EmailSender emailSender=new EmailSender();
                //emailSender.sendMessage("rensimon@cy-tech.fr", email);
                sendConfirmationEmail(email);
                //response.sendRedirect("MailServlet");
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Vue/infoCompte.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Cette adresse e-mail est déjà utilisée. Veuillez en choisir une autre.");
            }



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void sendConfirmationEmail(String recipientEmail) throws ServletException {
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
            message.setSubject("Confirmation de création de compte");
            message.setText("Merci pour votre inscription. Votre compte a été créé avec succès.");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServletException("Erreur lors de l'envoi de l'e-mail de confirmation", e);
        }
    }
}
