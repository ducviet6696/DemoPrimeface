/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demoprimeface.bean;

import java.util.UUID;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author ADMIN
 */
@ManagedBean
public class UserView {
    private String fName;
    private String lName;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
//        return UUID.randomUUID();
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    
    public void greeting(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Hello" +fName+", "+lName));
    }
}
