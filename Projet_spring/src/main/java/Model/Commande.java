package Model;

import java.math.BigDecimal;
import java.sql.Date;

public class Commande {
    private int numero;
    private Date dateDePaiement;
    private BigDecimal total;
    private String email;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getDateDePaiement() {
        return dateDePaiement;
    }

    public void setDateDePaiement(Date dateDePaiement) {
        this.dateDePaiement = dateDePaiement;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commande commande = (Commande) o;

        if (numero != commande.numero) return false;
        if (dateDePaiement != null ? !dateDePaiement.equals(commande.dateDePaiement) : commande.dateDePaiement != null)
            return false;
        if (total != null ? !total.equals(commande.total) : commande.total != null) return false;
        if (email != null ? !email.equals(commande.email) : commande.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numero;
        result = 31 * result + (dateDePaiement != null ? dateDePaiement.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
