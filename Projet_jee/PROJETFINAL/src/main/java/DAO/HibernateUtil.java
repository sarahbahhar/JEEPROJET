package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

        private static final SessionFactory sessionFactory;
        static
        {
            try
            {
                Configuration configuration = new Configuration();
                configuration.configure();
                sessionFactory = configuration.buildSessionFactory();
            }
            catch (Throwable ex)
            {
                System.err.println("Initial SessionFactorycreation failed."+ ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        public static SessionFactory getSessionFactory()
        {
            return sessionFactory;
        }
}

