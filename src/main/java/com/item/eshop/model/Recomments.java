package com.item.eshop.model;

import java.io.Serializable;
import java.util.Date;

public class Recomments implements Serializable {
    private String photo;

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto(){
        return photo;
    }
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.comments_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private Integer commentsId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.contents
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private String contents;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.shop_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private Integer shopId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.phone
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private String phone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.image
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private String image;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column recomments.sendtime
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    private Date sendtime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.id
     *
     * @return the value of recomments.id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.id
     *
     * @param id the value for recomments.id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.comments_id
     *
     * @return the value of recomments.comments_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public Integer getCommentsId() {
        return commentsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.comments_id
     *
     * @param commentsId the value for recomments.comments_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setCommentsId(Integer commentsId) {
        this.commentsId = commentsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.contents
     *
     * @return the value of recomments.contents
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public String getContents() {
        return contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.contents
     *
     * @param contents the value for recomments.contents
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setContents(String contents) {
        this.contents = contents;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.shop_id
     *
     * @return the value of recomments.shop_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.shop_id
     *
     * @param shopId the value for recomments.shop_id
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.phone
     *
     * @return the value of recomments.phone
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public String getPhone() {
        return phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.phone
     *
     * @param phone the value for recomments.phone
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.image
     *
     * @return the value of recomments.image
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public String getImage() {
        return image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.image
     *
     * @param image the value for recomments.image
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column recomments.sendtime
     *
     * @return the value of recomments.sendtime
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public Date getSendtime() {
        return sendtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column recomments.sendtime
     *
     * @param sendtime the value for recomments.sendtime
     *
     * @mbg.generated Sun Jul 22 15:06:19 CST 2018
     */
    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }
}