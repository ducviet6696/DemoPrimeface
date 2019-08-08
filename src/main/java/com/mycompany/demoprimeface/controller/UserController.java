/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demoprimeface.controller;

import com.mycompany.demoprimeface.bean.User;
import com.mycompany.demoprimeface.dao.UserDao;
import com.mycompany.demoprimeface.dao.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

/**
 *
 * @author ADMIN
 */
@ManagedBean
@SessionScoped
public class UserController {

    private User selectedUser;
    List<User> users;
    List<User> usersSearch;
    UserDao dao = new UserDao();
    private String keySeach;

    private int totalPage = 0;
    private static final int RECORD_PER_PAGE = 5;

    public int getTotalPage() {
        return (int) users.size() / RECORD_PER_PAGE;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    Predicate<User> searchByUid = u -> {
        return u.getUid().contains(keySeach);
    };
    Predicate<User> searchByFirstName = u -> {
        return u.getFirstName().contains(keySeach);
    };
    Predicate<User> searchByLastName = u -> {
        return u.getLastName().contains(keySeach);
    };
    Predicate<User> searchByEmail = u -> {
        return u.getEmail().contains(keySeach);
    };

    public void search() {
//        if (keySeach.trim().isEmpty() || keySeach == null) {
//            users = dao.getAllList();
//        } else {
//            users = users.stream()
////                    .filter(u -> u.getUid().contains(keySeach) || u.getFirstName().contains(keySeach) || u.getLastName().contains(keySeach) || u.getEmail().contains(keySeach))
//                    .filter(searchByUid.or(searchByFirstName).or(searchByLastName).or(searchByEmail))
//                    .limit(5)
//                    .collect(Collectors.toList());
//        }
    }

    public void setUsersSearch(List<User> usersSearch) {
        this.usersSearch = usersSearch;
    }

    public String getKeySeach() {
        return keySeach;
    }

    public void setKeySeach(String keySeach) {
        this.keySeach = keySeach;
    }

    @PostConstruct
    public void loadListUser() {
        users = dao.getAllList();
//        users = dao.getAllListRepo();

    }

    public void delete() {
//        String uid = selectedUser.getUid();
//        System.out.println("uiddddddddddddddd: " +uid);
        dao.deleteByUid(selectedUser);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete User", selectedUser.getFirstName() +"is deleted!!!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void edit() {
        dao.edit(selectedUser);
    }

    public List<User> getUsers() {
        if (keySeach == null) {
            System.out.println("key nulll");
            return dao.getAllList();
        } else {
            System.out.println(keySeach);
            System.out.println("key not nulll");
            return dao.getAllList().stream()
                    //                    .filter(u -> u.getUid().contains(keySeach) || u.getFirstName().contains(keySeach) || u.getLastName().contains(keySeach) || u.getEmail().contains(keySeach))
                    .filter(searchByUid.or(searchByFirstName).or(searchByLastName).or(searchByEmail))
                    .collect(Collectors.toList());
        }
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

}
