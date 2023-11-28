package com.example.projectspring.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    @Column(name = "dateDePaiement")
    private Date dateDePaiement;
    @Column(name="total")
    private BigDecimal total;
    @Column(name="email")
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
        return email != null ? email.equals(commande.email) : commande.email == null;
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
