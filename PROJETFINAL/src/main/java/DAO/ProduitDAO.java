package DAO;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import Model.Moderateur;
import Model.Produit;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class ProduitDAO
{
    public static final String PATH_IMAGE = "/src/main/webapp/img/";

    public static void addProduct(Produit p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }
    public static List<Produit> getListProduit()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> allProducts = session.createQuery("FROM Produit").list();
        session.close();

        List<Produit> filteredProducts = allProducts.stream()
                .filter(p -> !isModerateurBanni(p))
                .collect(Collectors.toList());

        return filteredProducts;

    }
    public static List<Produit> getListProduitByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("FROM Produit P WHERE P.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }

    public static void removeProductByIdOfTable(int id)
    {
        Produit p;
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.createQuery("DELETE FROM Produitpanier WHERE produitId = :id")
                .setParameter("id", id)
                .executeUpdate();

        p = getProduitById(id);
        session.delete(p);
        session.getTransaction().commit();
        session.close();
    }
    public static List<Produit> getListProductByTitre(String titre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> allProducts = session.createQuery("FROM Produit P WHERE  P.titre LIKE :titre")
                .setParameter("titre", "%" + titre + "%").list();

        List<Produit> filteredProducts = allProducts.stream()
                .filter(p -> !isModerateurBanni(p))
                .collect(Collectors.toList());

        session.close();
        return filteredProducts;
    }


//
    public static Produit getProduitById(int produitId) {
        Produit produit = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            produit = session.get(Produit.class, produitId);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }



    public static void updateProduct(Produit produit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(produit); // Met à jour le produit dans la base de données

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Produit> getProductsByCategory(String categorie) {
        List<Produit> produit = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            produit = session.createQuery("FROM Produit P WHERE  P.categorie = :categorie")
                    .setParameter("categorie", categorie)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return produit;
    }

    public static boolean isModerateurBanni(Produit produit) {
        boolean isBanned = false;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            Moderateur moderateur = session.createQuery("FROM Moderateur M WHERE M.email = :email", Moderateur.class)
                    .setParameter("email", produit.getEmail())
                    .uniqueResult();

            // Vérifier si le modérateur est banni
            if (moderateur != null && moderateur.getDateBanni() != null) {
                isBanned = true;
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return isBanned;
    }

    public static void removeProductById(String p,String fileName,int id) {

        // Obtenez le chemin réel à partir du chemin relatif
        String absolutePath = p+"../../" + PATH_IMAGE;

        // Assurez-vous que le répertoire existe, sinon créez-le
        File imageDir = new File(absolutePath);


        // Maintenant, utilisez le chemin absolu pour écrire le fichier
        File file = new File(imageDir, fileName);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Image file deleted: " + fileName);
                removeProductByIdOfTable(id);
            } else {
                System.err.println("Failed to delete image file: " + fileName);
            }
        } else {
            System.err.println("Image file not found: " + fileName);
        }

    }

    }

