package com.example.office.data;

import org.litepal.crud.LitePalSupport;

public class Url_sheet extends LitePalSupport {
    private String name,url;
    private int id;

    public int getId() {
        return id;
    }

    public Url_sheet() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
