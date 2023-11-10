package DAO;

import java.util.List;

import com.sun.source.tree.CompilationUnitTree;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Infocompte;
import DAO.HibernateUtil;


public class InfoCompteDAO
{
    public static void addInfoCompte(Infocompte ic)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ic);
        session.getTransaction().commit();
        //session.close();
    }

    public static void removeFirstCompte()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List list = session.createQuery("from infocompte").list();

        session.delete(list.get( list.size() -1 ));
        session.getTransaction().commit();
        session.close();

    }

    /*public static List<Compte> getListCompte()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Compte> result = session.createQuery("from Compte").list();
        session.close();
        return result;
    }*/
    /*public boolean isUniqueEmail(String email){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        boolean unique=true;
        long nbEmail;
        try {
            transaction= session.beginTransaction();
            nbEmail= (long) session.createQuery("SELECT COUNT(*) FROM Compte C WHERE C.email = :email").setParameter("email", email).uniqueResult();
            unique=!(nbEmail>0);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return unique;

    }*/
    /*public boolean validate(String email, String password) {

        Transaction transaction = null;
        Compte compte = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // get an user object
            compte = (Compte) session.createQuery("FROM Compte C WHERE C.email = :email").setParameter("email", email).uniqueResult();

            if (compte != null && compte.getMotDePasse().equals(password)) {
                return true;
            }
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                //transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }*/


}

