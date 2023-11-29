package com.example.projectspring.Util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class EmailSender {

    private static final String USERNAME = "projetjee.cytech@gmail.com"; // Email credentials
    private static final String PASSWORD = "idgz udpj wywr dfpn";         // Application password
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    public static final String PATH_IMAGE = "/../../src/main/webapp/mail/";

    /**
     * Sends a password reset email with HTML content.
     *
     * @param recipientEmail The email address to send the password reset link to.
     * @param resetLink      The password reset link to include in the email.
     * @throws MessagingException If there is an error in sending the email.
     */
    public static void sendPasswordResetEmail(String path,String recipientEmail, String resetLink) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        String absPath=path+PATH_IMAGE;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Réinitialisation de votre mot de passe");

        try {
            String htmlContent = loadHtmlContent2(absPath+"mailPasswordForget.jsp", resetLink);
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (IOException e) {
            throw new MessagingException("Error loading HTML content", e);
        }

        Transport.send(message);
    }

    /**
     * Sends a welcome email to the specified email address.
     *
     * @param path          The base path to the email template.
     * @param recipientEmail The email address to send the welcome email to.
     * @param firstName     The first name of the recipient.
     * @param lastName      The last name of the recipient.
     * @throws MessagingException If there is an error in sending the email.
     */

    private static String loadHtmlContent1(String filePath, String firstName, String lastName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString()
                .replace("{prenom}", firstName)
                .replace("{nom}", lastName);
    }


    /**
     * Loads HTML content from a file and replaces a placeholder with the reset link.
     *
     * @param filePath  The path to the HTML file.
     * @param resetLink The reset link to be inserted into the HTML content.
     * @return The HTML content as a String.
     */
    private static String loadHtmlContent2(String filePath, String resetLink) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString().replace("{resetLink}", resetLink);
    }



    private static String loadHtmlContent3(String filePath, String email, String content) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }
        return contentBuilder.toString()
                .replace("{emailCompte}", email)
                .replace("{message}", content);
    }



    public static void sendWelcomeEmail(String path, String recipientEmail, String firstName, String lastName) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        String absPath = path + PATH_IMAGE;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Bienvenue sur Arcadia");

        try {
            String htmlContent = loadHtmlContent1(absPath + "mailOfWelcome.jsp", firstName, lastName);
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (IOException e) {
            throw new MessagingException("Error loading HTML content", e);
        }

        Transport.send(message);
    }

    public static void sendContact(String path, String recipientEmail,String email, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);

        String absPath = path + PATH_IMAGE;

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Bienvenue sur Arcadia");

        try {
            String htmlContent = loadHtmlContent3(absPath + "mailContact.jsp", email, content);
            message.setContent(htmlContent, "text/html; charset=utf-8");
        } catch (IOException e) {
            throw new MessagingException("Error loading HTML content", e);
        }

        Transport.send(message);
    }


}
