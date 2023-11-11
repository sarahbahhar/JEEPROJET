package DAO;

import Model.Demandemoderateur;
import Model.Moderateur;
import org.hibernate.Session;

import java.util.List;

public class DemandeModerateurDAO {
    public static void addModerator(Model.Demandemoderateur dM)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(dM);
        session.getTransaction().commit();
        session.close();
    }
    public List<Demandemoderateur> getListModerateurWaiting() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Demandemoderateur> result = session.createQuery("from Demandemoderateur", Demandemoderateur.class).list();
        session.close();
        return result;
    }
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
}
