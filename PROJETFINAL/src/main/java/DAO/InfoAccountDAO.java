package DAO;

import Model.Infocompte;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class InfoAccountDAO {

    public static String[] getFullNameByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String[] fullName = new String[2]; // Un tableau pour stocker le nom et le prénom

        try {
            Infocompte infoAccount = (Infocompte) session.createQuery("FROM Infocompte I WHERE I.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();

            if (infoAccount != null) {
                fullName[0] = infoAccount.getPrenom();
                fullName[1] = infoAccount.getNom();
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return fullName;
    }

}
