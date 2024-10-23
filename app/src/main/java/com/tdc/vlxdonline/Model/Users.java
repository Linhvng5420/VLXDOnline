package com.tdc.vlxdonline.Model;

public class Users {
    private String email, pass;
    private String type_user;

    public Users(String email, String pass, String type_user) {
        this.email = email;
        this.pass = pass;
        this.type_user = type_user;
    }

    public Users() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }
}
