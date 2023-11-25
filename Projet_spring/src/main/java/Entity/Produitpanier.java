package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "produitpanier")
@IdClass(ProduitpanierPK.class)
public class Produitpanier {
    @Id
    private String email;

    @Id
    @Column(name = "produit_id")
    private int produitId;

    private int quantite;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Produitpanier that = (Produitpanier) o;

        if (produitId != that.produitId) return false;
        if (quantite != that.quantite) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + produitId;
        result = 31 * result + quantite;
        return result;
    }
}
