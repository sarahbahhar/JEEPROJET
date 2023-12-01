package DAO;

import java.io.File;
import java.util.List;

import Model.Compte;
import Model.Produit;
import Model.Token;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.security.SecureRandom;
public class TokenDAO
{

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private static SecureRandom random = new SecureRandom();

    /**
     * addToken
     * @param email
     */
    public static void addToken(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Token t = new Token();
        t.setEmail(email);
        t.setToken("0");
        session.save(t);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * changeTokenByEmail
     * @param email
     * @param tokenValue
     */
    public static void changeTokenByEmail(String email, String tokenValue) {
        Token token = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            token = session.get(Token.class, email);
            token.setToken(tokenValue);
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * getTokenValueByEmail
     * @param email
     * @return String
     */

    public static String getTokenValueByEmail(String email) {
            Token token = null;

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                token = session.get(Token.class, email);
                return token.getToken();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "error";
        }

    /**
     * generateResetToken
     * @return String
     */
    public static String generateResetToken() {
            StringBuilder token = new StringBuilder(LENGTH);
            for (int i = 0; i < LENGTH; i++) {
                token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return token.toString();
        }

}

