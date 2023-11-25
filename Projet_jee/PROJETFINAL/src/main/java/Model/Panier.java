package Model;

import java.math.BigDecimal;

public class Panier {
    private String email;
    private BigDecimal ht;
    private BigDecimal tva;
    private BigDecimal ttc;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getHt() {
        return ht;
    }

    public void setHt(BigDecimal ht) {
        this.ht = ht;
    }

    public BigDecimal getTva() {
        return tva;
    }

    public void setTva(BigDecimal tva) {
        this.tva = tva;
    }

    public BigDecimal getTtc() {
        return ttc;
    }

    public void setTtc(BigDecimal ttc) {
        this.ttc = ttc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Panier panier = (Panier) o;

        if (email != null ? !email.equals(panier.email) : panier.email != null) return false;
        if (ht != null ? !ht.equals(panier.ht) : panier.ht != null) return false;
        if (tva != null ? !tva.equals(panier.tva) : panier.tva != null) return false;
        if (ttc != null ? !ttc.equals(panier.ttc) : panier.ttc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (ht != null ? ht.hashCode() : 0);
        result = 31 * result + (tva != null ? tva.hashCode() : 0);
        result = 31 * result + (ttc != null ? ttc.hashCode() : 0);
        return result;
    }
}
