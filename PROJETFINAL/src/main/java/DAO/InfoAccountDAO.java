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
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import Model.Infocompte;


public class InfoAccountDAO
{
    public static void addInfoCompte(Infocompte ic)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(ic);
        session.getTransaction().commit();
        session.close();
    }

    public static void removeFirstCompte()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List list = session.createQuery("from Infocompte").list();

        session.delete(list.get( list.size() -1 ));
        session.getTransaction().commit();
        session.close();

    }

    public Infocompte getInfoCompte(String email){
        Infocompte ic=null;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction transaction=null;
        try{
            transaction=session.beginTransaction();
            ic= (Infocompte) session.createQuery("FROM Infocompte WHERE email = :email").setParameter("email", email).uniqueResult();
            transaction.commit();}
        catch(Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return fullName;
    }

}

        return ic;
    }

    public void updateInfoCompte(String email, Infocompte updated){
        //Session session = HibernateUtil.getSessionFactory().openSession();;
        Session session=null;
        Transaction transaction=null;
        //Transaction transaction = null;
        //Infocompte toChange;
        /*try{

            transaction = session.beginTransaction();
            toChange= (Infocompte) session.createQuery("FROM Infocompte WHERE email = :email").setParameter("email", email).uniqueResult();
            toChange.setPrenom(updated.getPrenom());
            toChange.setNom(updated.getNom());
            toChange.setDateNaissance(updated.getDateNaissance());
            toChange.setTelephone(updated.getTelephone());
            toChange.setAdresse(updated.getAdresse());
            toChange.setVille(updated.getVille());
            toChange.setCodePostal(updated.getCodePostal());
            toChange.setPays(updated.getPays());
            transaction.commit();

        }catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }*/
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            // Charger l'Infocompte existant à partir de la base de données
            Infocompte toChange = (Infocompte) session.get(Infocompte.class, email);

            // Mettre à jour les champs de l'Infocompte existant avec les nouvelles valeurs
            /*existingInfocompte.setPrenom(updated.getPrenom());
            existingInfocompte.setNom(updated.getNom());
            existingInfocompte.setEmail(updated.getEmail());*/
            // ... (mettez à jour d'autres champs au besoin)
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

