package DAO;

import Model.Infocompte;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;



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

    public static void addInfoCompte(Infocompte ic) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ic);
        session.getTransaction().commit();
        session.close();
    }


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

    public void updateInfoCompte(String email, Infocompte updated) {
        //Session session = HibernateUtil.getSessionFactory().openSession();;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Charger l'Infocompte existant à partir de la base de données
            Infocompte toChange = (Infocompte) session.get(Infocompte.class, email);

            // Mettre à jour les champs de l'Infocompte existant avec les nouvelles valeurs

            toChange.setPrenom(updated.getPrenom());
            toChange.setNom(updated.getNom());
            toChange.setDateNaissance(updated.getDateNaissance());
            toChange.setTelephone(updated.getTelephone());
            toChange.setAdresse(updated.getAdresse());
            toChange.setVille(updated.getVille());
            toChange.setCodePostal(updated.getCodePostal());
            toChange.setPays(updated.getPays());

            // Enregistrer les modifications dans la base de données
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


