package Model;

import java.io.Serializable;

public class ProduitcommandePK implements Serializable {
    private int produitId;
    private int commandeNumero;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduitcommandePK that = (ProduitcommandePK) o;

        if (produitId != that.produitId) return false;
        if (commandeNumero != that.commandeNumero) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = produitId;
        result = 31 * result + commandeNumero;
        return result;
    }
}
