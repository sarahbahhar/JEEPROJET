package Model;

public class Moderateur {
    private String email;
    private byte peutAjouterProduit;
    private byte peutSupprimerProduit;
    private int maxProduitsLigne;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getPeutAjouterProduit() {
        return peutAjouterProduit;
    }

    public void setPeutAjouterProduit(byte peutAjouterProduit) {
        this.peutAjouterProduit = peutAjouterProduit;
    }

    public byte getPeutSupprimerProduit() {
        return peutSupprimerProduit;
    }

    public void setPeutSupprimerProduit(byte peutSupprimerProduit) {
        this.peutSupprimerProduit = peutSupprimerProduit;
    }

    public int getMaxProduitsLigne() {
        return maxProduitsLigne;
    }

    public void setMaxProduitsLigne(int maxProduitsLigne) {
        this.maxProduitsLigne = maxProduitsLigne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moderateur that = (Moderateur) o;

        if (peutAjouterProduit != that.peutAjouterProduit) return false;
        if (peutSupprimerProduit != that.peutSupprimerProduit) return false;
        if (maxProduitsLigne != that.maxProduitsLigne) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (int) peutAjouterProduit;
        result = 31 * result + (int) peutSupprimerProduit;
        result = 31 * result + maxProduitsLigne;
        return result;
    }
}
