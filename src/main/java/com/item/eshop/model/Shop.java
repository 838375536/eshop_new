package com.item.eshop.model;

import java.io.Serializable;

public class Shop implements Serializable {
    private Integer id;
    private String shop_name;
    private String introduce;
    private String contact;
    private Integer school_id;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private Integer status;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    private String address;
    private Integer user_id;
    private String image;
    private String gps;
    private Integer delivery_range;
    private String shop_type;
    private String start_time;
    private String end_time;
    private String shop_other;
    private Integer min_price;
private String province;
private String school;

    public Integer getMin_price() {
        return min_price;
    }

    public void setMin_price(Integer min_price) {
        this.min_price = min_price;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getShop_other() {
        return shop_other;
    }

    public void setShop_other(String shop_other) {
        this.shop_other = shop_other;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public Integer getDelivery_range() {
        return delivery_range;
    }

    public void setDelivery_range(Integer delivery_range) {
        this.delivery_range = delivery_range;
    }

    public String getShop_type() {
        return shop_type;
    }

    public void setShop_type(String shop_type) {
        this.shop_type = shop_type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String Integerroduce) {
        this.introduce = Integerroduce;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getSchool_id() {
        return school_id;
    }

    public void setSchool_id(Integer school_id) {
        this.school_id = school_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + shop_name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", contact='" + contact + '\'' +
                ", school_id=" + school_id +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", user_id=" + user_id +
                ", image='" + image + '\'' +
                ", gps='" + gps + '\'' +
                ", range=" + delivery_range +
                ", shop_type='" + shop_type + '\'' +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", shop_other='" + shop_other + '\'' +
                '}';
    }


}
