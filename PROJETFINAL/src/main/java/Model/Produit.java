package Model;

import java.math.BigDecimal;

public class Produit {
    private int id;
    private String titre;
    private String description;
    private int stock;
    private String email;
    private String nomImage;
    private String miniDescription;
    private BigDecimal prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }

    public String getMiniDescription() {
        return miniDescription;
    }

    public void setMiniDescription(String miniDescription) {
        this.miniDescription = miniDescription;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produit produit = (Produit) o;

        if (id != produit.id) return false;
        if (stock != produit.stock) return false;
        if (titre != null ? !titre.equals(produit.titre) : produit.titre != null) return false;
        if (description != null ? !description.equals(produit.description) : produit.description != null) return false;
        if (email != null ? !email.equals(produit.email) : produit.email != null) return false;
        if (nomImage != null ? !nomImage.equals(produit.nomImage) : produit.nomImage != null) return false;
        if (miniDescription != null ? !miniDescription.equals(produit.miniDescription) : produit.miniDescription != null)
            return false;
        if (prix != null ? !prix.equals(produit.prix) : produit.prix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + stock;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (nomImage != null ? nomImage.hashCode() : 0);
        result = 31 * result + (miniDescription != null ? miniDescription.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        return result;
    }
}
