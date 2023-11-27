package com.example.projectspring.Entity;

import java.io.Serializable;

public class ProduitpanierPK implements Serializable {
    private String email;
    private int produitId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProduitpanierPK that = (ProduitpanierPK) o;

        if (produitId != that.produitId) return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + produitId;
        return result;
    }
}
