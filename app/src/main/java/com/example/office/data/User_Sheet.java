package com.example.office.data;

import org.litepal.crud.LitePalSupport;

public class User_Sheet extends LitePalSupport {
    private String name,password;
    private int id;
    private long date;


    public long getDate() {
        return date;
    }
    public int getId() {
        return id;
    }
    public void setId(int id){}

    public User_Sheet() {
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
