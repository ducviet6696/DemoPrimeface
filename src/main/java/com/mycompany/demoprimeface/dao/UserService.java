/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demoprimeface.dao;

import com.mycompany.demoprimeface.bean.User;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ADMIN
 */
//@ManagedBean(name = "userSv")
@Service
public class UserService {

    @Autowired
    UserRepository repo;

    public List<User> getAllUser() {
        List<User> users = repo.findAll();
        System.out.println("List user: " + users);
        return users;
    }

    public void deleteById(String id) {
        if (repo.findById(id).isPresent()) {
            repo.deleteById(id);
        } else {
            System.out.println("User does not exits!!!");
        }
    }

    public void save(User user) {
        repo.save(user);
    }
}
