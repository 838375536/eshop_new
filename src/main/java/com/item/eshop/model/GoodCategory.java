package com.item.eshop.model;

import java.io.Serializable;

public class GoodCategory implements Serializable {
    private Integer id;
    private Integer sort_no;
    private String name;
    private Integer shop_id;

    public Integer getShop_id() {
        return shop_id;
    }

    public void setShop_id(Integer shop_id) {
        this.shop_id = shop_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort_no() {
        return sort_no;
    }

    public void setSort_no(Integer sort_no) {
        this.sort_no = sort_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
