package com.example.projectspring.Util;

import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.activation.*;

import java.util.Properties;

public class EmailSender {
    public static void sendMessage(String sender, String destination){
        String host="localhost";
        Properties properties = System.getProperties();

        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", host);
        //properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
        properties.setProperty("mail.from", sender);
        Session session = Session.getDefaultInstance(properties);

        try{
            MimeMessage msg= new MimeMessage(session);
            msg.setFrom(new InternetAddress(sender));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destination));
            msg.setSubject("Inscription réussie");
            msg.setText(" Bienvenue chez Milango!");

            Transport.send(msg);



        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
