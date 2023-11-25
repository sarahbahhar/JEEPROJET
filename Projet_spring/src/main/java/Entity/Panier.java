package Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "panier", schema = "projet_jee")
public class Panier {

    @Id
    private String email;

    @Column(name = "HT", precision = 10, scale = 2)
    private BigDecimal ht;

    @Column(name = "TVA", precision = 10, scale = 2)
    private BigDecimal tva;

    @Column(name = "TTC", precision = 10, scale = 2)
    private BigDecimal ttc;

    // Getter and Setter methods

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
}
