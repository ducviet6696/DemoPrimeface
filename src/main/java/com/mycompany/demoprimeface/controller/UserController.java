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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

/**
 *
 * @author ADMIN
 */
@ManagedBean
@SessionScoped
//@Controller
public class UserController {

//    @ManagedProperty("#{userSv}")
    @Autowired
    UserService userService;
    private User selectedUser;
    List<User> users;
    List<User> usersSearch;
    private int totalPage = 0;
    private static final int RECORD_PER_PAGE = 5;

    public int getTotalPage() {
        return (int) users.size()/RECORD_PER_PAGE;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public void search() {
        if (keySeach.trim().isEmpty() || keySeach == null) {
            users = dao.getAllList();
        } else {
            users = dao.getListBySearch(keySeach);
        }
    }

    public void setUsersSearch(List<User> usersSearch) {
        this.usersSearch = usersSearch;
    }
    UserDao dao = new UserDao();
    private String keySeach;

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
    }

    public void edit() {
        dao.edit(selectedUser);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public List<User> getUsers() {
        if (keySeach == null) {
            System.out.println("key nulll");
            return dao.getAllList();
        } else {
            System.out.println(keySeach);
            System.out.println("key not nulll");
            return dao.getListBySearch(keySeach);
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

    public UserDao getDao() {
        return dao;
    }

    public void setDao(UserDao dao) {
        this.dao = dao;
    }

}
