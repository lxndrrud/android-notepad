package com.lxndrrud.lab1.domain;

import lombok.Data;

import java.util.Date;

public class Item {
    private long id;
    private String text;
    private String title;

    private Date createdAt;

    public Item(long id, String title, String text, Date createdAt) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.createdAt = createdAt;
    }

    public Item(String title, String text, Date createdAt) {
        this.text = text;
        this.title = title;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getShortInfo() {
        return title + " - " + createdAt;
    }

    public String getFullInfo() {
        return id + "-" + title + "-" + text + "-" + createdAt;
    }
}
