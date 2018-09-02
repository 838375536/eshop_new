package com.item.eshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class User implements Serializable{
    private Integer id;

    private String phone;

    private String identityCard;

    private String photo;

    private String passwd;

    private String school;

    private int status;

    private Date sign_time;

    private BigDecimal money;

    private String avatar;

    private String username;

    private int score;

    private String other;

    public Date getSign_time() {
        return sign_time;
    }

    public void setSign_time(Date sign_time) {
        this.sign_time = sign_time;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public User(){}

    public User(String phone, String identityCard, String passwd, String school) {
        this.phone = phone;
        this.identityCard = identityCard;
        this.passwd = passwd;
        this.school = school;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard == null ? null : identityCard.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}