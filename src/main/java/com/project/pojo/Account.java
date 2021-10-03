package com.project.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    private String mail;
    private String password;
    private int role;
    private String name;
    private String address;
    private String phone;

    public Account() {
    }

    public Account(String mail, String password, int role, String name, String address, String phone) {
        this.mail = mail;
        this.password = password;
        this.role = role;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Id
    @Column(name = "user_mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String userMail) {
        this.mail = userMail;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "account_role")
    public int getRole() {
        return role;
    }

    public void setRole(int accountRole) {
        this.role = accountRole;
    }

    @Basic
    @Column(name = "user_name")
    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    @Basic
    @Column(name = "user_address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String userAddress) {
        this.address = userAddress;
    }

    @Basic
    @Column(name = "user_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String userPhone) {
        this.phone = userPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (role != account.role) return false;
        if (mail != null ? !mail.equals(account.mail) : account.mail != null) return false;
        if (password != null ? !password.equals(account.password) : account.password != null) return false;
        if (name != null ? !name.equals(account.name) : account.name != null) return false;
        if (address != null ? !address.equals(account.address) : account.address != null) return false;
        if (phone != null ? !phone.equals(account.phone) : account.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mail != null ? mail.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + role;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
