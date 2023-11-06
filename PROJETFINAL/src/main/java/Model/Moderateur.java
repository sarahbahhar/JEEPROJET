package Model;

public class Moderateur {
    private String email;
    private Byte peutAjouterProduit;
    private Byte peutSupprimerProduit;
    private Integer maxProduitsLigne;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getPeutAjouterProduit() {
        return peutAjouterProduit;
    }

    public void setPeutAjouterProduit(Byte peutAjouterProduit) {
        this.peutAjouterProduit = peutAjouterProduit;
    }

    public Byte getPeutSupprimerProduit() {
        return peutSupprimerProduit;
    }

    public void setPeutSupprimerProduit(Byte peutSupprimerProduit) {
        this.peutSupprimerProduit = peutSupprimerProduit;
    }

    public Integer getMaxProduitsLigne() {
        return maxProduitsLigne;
    }

    public void setMaxProduitsLigne(Integer maxProduitsLigne) {
        this.maxProduitsLigne = maxProduitsLigne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moderateur that = (Moderateur) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (peutAjouterProduit != null ? !peutAjouterProduit.equals(that.peutAjouterProduit) : that.peutAjouterProduit != null)
            return false;
        if (peutSupprimerProduit != null ? !peutSupprimerProduit.equals(that.peutSupprimerProduit) : that.peutSupprimerProduit != null)
            return false;
        if (maxProduitsLigne != null ? !maxProduitsLigne.equals(that.maxProduitsLigne) : that.maxProduitsLigne != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (peutAjouterProduit != null ? peutAjouterProduit.hashCode() : 0);
        result = 31 * result + (peutSupprimerProduit != null ? peutSupprimerProduit.hashCode() : 0);
        result = 31 * result + (maxProduitsLigne != null ? maxProduitsLigne.hashCode() : 0);
        return result;
    }
}
