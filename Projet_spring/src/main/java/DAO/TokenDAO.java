package DAO;

import Entity.Token;
import org.hibernate.Session;

import java.security.SecureRandom;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDAO
{

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int LENGTH = 10;
    private static SecureRandom random = new SecureRandom();
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



        public static String generateResetToken() {
            StringBuilder token = new StringBuilder(LENGTH);
            for (int i = 0; i < LENGTH; i++) {
                token.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
            }
            return token.toString();
        }

}

