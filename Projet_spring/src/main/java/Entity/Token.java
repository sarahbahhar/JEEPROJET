package Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token", schema = "projet_jee")
public class Token {

    @Id
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "token", nullable = false, length = 50)
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token1 = (Token) o;

        if (email != null ? !email.equals(token1.email) : token1.email != null) return false;
        if (token != null ? !token.equals(token1.token) : token1.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}

