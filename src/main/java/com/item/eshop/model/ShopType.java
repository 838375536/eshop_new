package com.item.eshop.model;

import java.io.Serializable;
import java.util.List;

public class ShopType implements Serializable {
    private int id;
    private String name;
    private List<School> school;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<School> getSchool() {
        return school;
    }

    public void setSchool(List<School> school) {
        this.school = school;
    }
}
