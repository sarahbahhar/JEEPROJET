package DAO;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import Model.Moderateur;
import Model.Produit;
import Model.Produitpanier;
import org.hibernate.Session;
import org.hibernate.Transaction;
public class ProduitDAO
{
    public static final String PATH_IMAGE = "/src/main/webapp/img/";

    /**
     * Add a new product in the database
     * @param p
     */
    public static void addProduct(Produit p)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(p);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Get the list of the product
     * @return
     */
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

    /**
     * Get the list of a product by an account email
     * @param email
     * @return
     */
    public static List<Produit> getListProduitByEmail(String email)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produit> result = session.createQuery("FROM Produit P WHERE P.email = :email")
                .setParameter("email", email).list();
        session.close();
        return result;
    }

    /**
     * Remove a product by the id if table in the database
     * @param id
     */
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

    /**
     * Get the list of the product by the title
     * @param titre
     * @return list of product
     */
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


    /**
     * Get a product by the id
     * @param produitId
     * @return product
     */
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

    /**
     * Update the information of a product in the database
     * @param produit
     */
    public static void updateProduct(Produit produit) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.update(produit); // Met à jour le produit dans la base de données

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the list of product by the category
     * @param categorie
     * @return list of products
     */
    public static List<Produit> getProductsByCategory(String categorie) {
        List<Produit> allProducts = null;

        List<Produit> filteredProducts = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            allProducts = session.createQuery("FROM Produit P WHERE  P.categorie = :categorie")
                    .setParameter("categorie", categorie)
                    .list();

            filteredProducts = allProducts.stream()
                    .filter(p -> !isModerateurBanni(p))
                    .collect(Collectors.toList());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return filteredProducts;
    }

    /**
     * Verify if the moderator is banned
     * @param produit
     * @return boolean
     */
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

    /**
     * Remove a product in the database by the id in the database
     * @param p
     * @param fileName1
     * @param fileName2
     * @param id
     */
    public static void removeProductById(String p,String fileName1,String fileName2,int id) {

        String absolutePath = p+"../../" + PATH_IMAGE;

        File imageDir = new File(absolutePath);


        File file = new File(imageDir, fileName1);
        File file2 = new File(imageDir, fileName2);

        if (file.exists()&&file2.exists()) {
            if (file.delete()&&file2.delete()) {
                System.out.println("Image file deleted: " + fileName1+fileName2);

            } else {
                System.err.println("Failed to delete image file: " + fileName1+fileName2);
            }
        } else {
            System.err.println("Image file not found: " + fileName1+fileName2);
        }
        removeProductByIdOfTable(id);

    }

    /**
     * Remove a Moderator's product
     * @param localisation
     * @param email
     */
    public static void removeProductByModerator(String localisation,String email) {
        List<Produit> produits =  getListProduitByEmail(email);
        for(Produit produit : produits){
            PanierDAO.removeProduitPanierById(produit.getId());
            removeProductById(localisation,produit.getNomImage(),produit.getNomImage2(),produit.getId());

        }


    }

    }

