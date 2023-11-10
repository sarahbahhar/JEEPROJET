package DAO;

import Model.Moderateur;
import org.hibernate.Session;
import java.util.List;
import Model.Moderateur;

public class ModeratorDAO {
    public List<Moderateur> getListModerateur() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Moderateur> result = session.createQuery("from Moderateur", Moderateur.class).list();
        session.close();
        return result;
    }
    public static void addModerator(Model.Moderateur m)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(m);
        session.getTransaction().commit();
        session.close();
    }
}
