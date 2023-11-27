package com.example.projectspring.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "demandemoderateur")
public class Demandemoderateur {
    @Id
    private String email;

    private String message;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Demandemoderateur that = (Demandemoderateur) o;

        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}
