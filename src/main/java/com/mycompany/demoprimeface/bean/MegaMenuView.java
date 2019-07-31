package com.mycompany.demoprimeface.bean;


import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ADMIN
 */


@ManagedBean(name = "menu")
public class MegaMenuView {
    private String orientation = "horizontal";
    private String text;

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getText() {
        return text;
    }
     public void handleKeyEvent() {
        text = text.toUpperCase();
    }

    public void setText(String text) {
        this.text = text;
    }
     
    
}
