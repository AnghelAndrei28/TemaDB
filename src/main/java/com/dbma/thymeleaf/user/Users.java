package com.dbma.thymeleaf.user;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "users")
public class Users {
    @Id
    private int id;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    private String password;
    @Column(name = "mobile_number")
    private String mobileNumber;
    private int age;

    public Users() {
    }

    public Users(int id, String fullName, String email, String password, String mobileNumber, int age) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
