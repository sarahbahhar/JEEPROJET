package DAO;

import Model.Infocompte;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;



public class InfoAccountDAO {
    /**
     * getFullNameByEmail
     * @param email
     * @return string
     */

    public static String[] getFullNameByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String[] fullName = new String[2];

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

    /**
     * add Info to an account
     * @param ic
     */
    public static void addInfoCompte(Infocompte ic) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ic);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * getInfo for an account
     * @param email
     * @return InfoCompte
     */
    public Infocompte getInfoCompte(String email) {
        Infocompte ic = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ic = (Infocompte) session.createQuery("FROM Infocompte WHERE email = :email").setParameter("email", email).uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }


        return ic;
    }

    /**
     * updateInfoCompte
     * @param email
     * @param updated
     */
    public void updateInfoCompte(String email, Infocompte updated) {
        //Session session = HibernateUtil.getSessionFactory().openSession();;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            /
            Infocompte toChange = (Infocompte) session.get(Infocompte.class, email);



            toChange.setPrenom(updated.getPrenom());
            toChange.setNom(updated.getNom());
            toChange.setDateNaissance(updated.getDateNaissance());
            toChange.setTelephone(updated.getTelephone());
            toChange.setAdresse(updated.getAdresse());
            toChange.setVille(updated.getVille());
            toChange.setCodePostal(updated.getCodePostal());
            toChange.setPays(updated.getPays());


            session.update(toChange);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}


