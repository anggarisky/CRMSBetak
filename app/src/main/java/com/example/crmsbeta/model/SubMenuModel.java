package com.example.crmsbeta.model;

public class SubMenuModel {
    private String title;
    private int id;

    public SubMenuModel(int id, String title) {
        this.title = title;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
