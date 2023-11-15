package Model;

public class Commentaires {
    private int id;
    private Integer idProduit;
    private int note;
    private String emailVendeur;
    private String commentaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getEmailVendeur() {
        return emailVendeur;
    }

    public void setEmailVendeur(String emailVendeur) {
        this.emailVendeur = emailVendeur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Commentaires that = (Commentaires) o;

        if (id != that.id) return false;
        if (note != that.note) return false;
        if (idProduit != null ? !idProduit.equals(that.idProduit) : that.idProduit != null) return false;
        if (emailVendeur != null ? !emailVendeur.equals(that.emailVendeur) : that.emailVendeur != null) return false;
        if (commentaire != null ? !commentaire.equals(that.commentaire) : that.commentaire != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (idProduit != null ? idProduit.hashCode() : 0);
        result = 31 * result + note;
        result = 31 * result + (emailVendeur != null ? emailVendeur.hashCode() : 0);
        result = 31 * result + (commentaire != null ? commentaire.hashCode() : 0);
        return result;
    }
}
