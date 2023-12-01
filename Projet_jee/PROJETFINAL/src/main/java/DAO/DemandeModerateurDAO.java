package DAO;

import Model.Demandemoderateur;
import Model.Moderateur;
import org.hibernate.Session;

import java.util.List;

public class DemandeModerateurDAO {

    /**
     * Add moderator to the database
     * @param dM
     */
    public static void addModerator(Model.Demandemoderateur dM)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(dM);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Get a list of waiting moderator
     * @return List of moderator
     */
    public List<Demandemoderateur> getListModerateurWaiting() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Demandemoderateur> result = session.createQuery("from Demandemoderateur", Demandemoderateur.class).list();
        session.close();
        return result;
    }

    /**
     * Remove dissmissedModerator to the database
     * @param email
     */
    public static void removeDissmissedModerator(String email)
    {
        Demandemoderateur dM;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        dM = (Demandemoderateur) session.createQuery("FROM Demandemoderateur W WHERE W.email = :email").setParameter("email", email).uniqueResult();
        List list = session.createQuery("from Demandemoderateur").list();
        session.delete(dM);
        session.getTransaction().commit();
        session.close();

    }

    /**
     * verify if Email is in the moderator requests
     * @param email
     * @return boolean
     */
    public static boolean isEmailInModeratorRequests(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();

            String hql = "SELECT count(*) FROM Demandemoderateur WHERE email = :email";
            Long count = (Long) session.createQuery(hql)
                    .setParameter("email", email)
                    .uniqueResult();
            session.getTransaction().commit();

            return count != null && count > 0;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
