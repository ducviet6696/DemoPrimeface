/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demoprimeface.bean;

import com.mycompany.demoprimeface.dao.UserDao;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author ADMIN
 */
//@Document(collection = "Viet")

@ManagedBean
public class User {

//    @Id
    private String uid;
//    @Field("first_name")
    private String firstName;
//    @Field("last_name")
    private String lastName;
    private String email;

    public User() {
    }

    public String getUid() {
        return uid;
    }

    public User(String firstName, String lastName, String email) {
        this.uid = getRandomId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String uid, String firstName, String lastName, String email) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "uid=" + uid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + '}';
    }

    public String getRandomId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }
    public void add(){
        UserDao.getInstance().add(this);
    }

}
