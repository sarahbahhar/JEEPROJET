package Model;

public class Produitpanier {
    private int produitId;
    private int quantite;

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

        return true;
    }

    @Override
    public int hashCode() {
        int result = produitId;
        result = 31 * result + quantite;
        return result;
    }
}
