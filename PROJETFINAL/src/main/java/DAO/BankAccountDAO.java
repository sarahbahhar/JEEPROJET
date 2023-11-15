package DAO;

import org.hibernate.Session;

public class BankAccountDAO {
    public static void addBankAccount(Model.Comptebancaire cb)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(cb);
        session.getTransaction().commit();
        session.close();
    }
}
