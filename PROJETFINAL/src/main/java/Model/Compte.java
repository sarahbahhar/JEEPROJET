package Model;

public class Compte {
    private String email;
    private String motDePasse;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Compte compte = (Compte) o;

        if (email != null ? !email.equals(compte.email) : compte.email != null) return false;
        if (motDePasse != null ? !motDePasse.equals(compte.motDePasse) : compte.motDePasse != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (motDePasse != null ? motDePasse.hashCode() : 0);
        return result;
    }
}
