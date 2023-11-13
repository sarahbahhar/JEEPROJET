package Model;

import java.math.BigDecimal;

public class Produitcommande {
    private int id;
    private int commandeNumero;
    private int quantite;
    private String titre;
    private BigDecimal prix;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

        Produitcommande that = (Produitcommande) o;

        if (id != that.id) return false;
        if (commandeNumero != that.commandeNumero) return false;
        if (quantite != that.quantite) return false;
        if (titre != null ? !titre.equals(that.titre) : that.titre != null) return false;
        if (prix != null ? !prix.equals(that.prix) : that.prix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + commandeNumero;
        result = 31 * result + quantite;
        result = 31 * result + (titre != null ? titre.hashCode() : 0);
        result = 31 * result + (prix != null ? prix.hashCode() : 0);
        return result;
    }
}
