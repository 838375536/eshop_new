package com.item.eshop.model;


import java.io.Serializable;

public class Notice implements Serializable {

    private  String title;

    private  Integer id;

    private  String content;

    private  Integer category;

    private  String times;

    private  int status;

    private  String image;

    private  Integer foreign_id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getForeign_id() {
        return foreign_id;
    }

    public void setForeign_id(Integer foreign_id) {
        this.foreign_id = foreign_id;
    }
}