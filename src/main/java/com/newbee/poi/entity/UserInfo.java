package com.newbee.poi.entity;

import java.io.Serializable;

/**
 * Created by Major on 2016/11/11.
 */
public class UserInfo implements Serializable{
    private int id;
    private String userName;
    private String userPass;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
