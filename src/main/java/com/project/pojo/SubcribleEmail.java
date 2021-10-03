package com.project.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Subcrible_Email", schema = "dbo", catalog = "ShoppingDB")
public class SubcribleEmail {
    private int id;
    private String email;

    public SubcribleEmail() {
    }

    public SubcribleEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "email")
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

        SubcribleEmail that = (SubcribleEmail) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
