package com.item.eshop.model;

import java.io.Serializable;

public class Status implements Serializable {
    private Integer status;
    private Integer num;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
