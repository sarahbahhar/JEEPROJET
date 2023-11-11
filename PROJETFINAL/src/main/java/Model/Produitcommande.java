package Model;

public class Produitcommande {
    private int produitId;
    private int commandeNumero;
    private int quantite;

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public int getCommandeNumero() {
        return commandeNumero;
    }

    public void setCommandeNumero(int commandeNumero) {
        this.commandeNumero = commandeNumero;
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

        Produitcommande that = (Produitcommande) o;

        if (produitId != that.produitId) return false;
        if (commandeNumero != that.commandeNumero) return false;
        if (quantite != that.quantite) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = produitId;
        result = 31 * result + commandeNumero;
        result = 31 * result + quantite;
        return result;
    }
}
