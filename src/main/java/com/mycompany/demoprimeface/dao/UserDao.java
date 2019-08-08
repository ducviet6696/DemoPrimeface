/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demoprimeface.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mycompany.demoprimeface.bean.User;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ADMIN
 */
public class UserDao {

    public static final int RECORDS_PER_PAGE = 5;
    public static UserDao INSTANCE = null;
    private MongoClient mongo;
    private DB db;
    private DBCollection table;
    DBCursor cursor;
    @Autowired
    UserRepository repo;

    public UserDao() {
        mongo = new MongoClient("localhost", 27017);
        db = mongo.getDB("BookStore");
        table = db.getCollection("Viet");
    }

    public static UserDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    //add user
    public void add(User user) {
        BasicDBObject document = new BasicDBObject();
        document.put("uid", user.getRandomId());
        document.put("first_name", user.getFirstName());
        document.put("last_name", user.getLastName());
        document.put("email", user.getEmail());
        table.insert(document);
    }

    public List<User> getAllList() {
        List<User> listUser = new ArrayList<>();

        cursor = table.find();
        while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            User user = new User((String) document.get("uid"), (String) document.get("first_name"), (String) document.get("last_name"), (String) document.get("email"));
            listUser.add(user);
        }
//        System.out.println("list: " + listUser);
        return listUser;
    }

    public List<User> getListBySearch(String keyword) {
        List<User> listUser = new ArrayList<>();
        String regex = ".*" + keyword + ".*";
        Pattern pattern = Pattern.compile(regex);
        System.out.println("pattern " + pattern);
        DBObject whereClause = new BasicDBObject("first_name", pattern);
        System.out.println("whereClause: " + whereClause);
        cursor = table.find(whereClause);
        while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            User user = new User((String) document.get("uid"), (String) document.get("first_name"), (String) document.get("last_name"), (String) document.get("email"));
            listUser.add(user);
        }
        return listUser;
    }

//    public List<User> getAllListRepo() {
//        return repo.findAll();
//    }
    public void deleteByUid(User user) {
        DBObject whereClause = new BasicDBObject("uid", user.getUid());
        table.remove(whereClause);
    }

    public void edit(User user) {
        DBObject whereClause = new BasicDBObject("uid", user.getUid());

        DBObject values = new BasicDBObject();
        values.put("uid", user.getUid());
        values.put("first_name", user.getFirstName());
        values.put("last_name", user.getLastName());
        values.put("email", user.getEmail());
        table.update(whereClause, values);
    }
}
