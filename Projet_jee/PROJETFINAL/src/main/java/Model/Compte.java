package Model;

import jakarta.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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
    public void setAndHashMotDePasse(String motDePasse) {
        this.motDePasse = hashPassword(motDePasse);
    }
    public boolean isMotDePasseCorrect(String motDePasseFourni) {
        try {
            String hashedPassword = hashPassword(motDePasseFourni);

            return hashedPassword.equals(this.motDePasse);
        } catch (Exception e) {
            // Gérer les exceptions ici
            return false;
        }
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

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Problème lors du hachage du mot de passe", e);
        }
    }
}
