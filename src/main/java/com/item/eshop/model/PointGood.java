package com.item.eshop.model;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;

import java.io.Serializable;
import java.util.Date;

public class PointGood implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.id
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer id;
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /**

     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.point
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer point;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.name
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.createby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer createby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.creattime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Date creattime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.updateby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer updateby;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.updatetime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Date updatetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.status
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column point_good.count
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    private Integer count;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.id
     *
     * @return the value of point_good.id
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.id
     *
     * @param id the value for point_good.id
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.point
     *
     * @return the value of point_good.point
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.point
     *
     * @param point the value for point_good.point
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.name
     *
     * @return the value of point_good.name
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.name
     *
     * @param name the value for point_good.name
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.createby
     *
     * @return the value of point_good.createby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getCreateby() {
        return createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.createby
     *
     * @param createby the value for point_good.createby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setCreateby(Integer createby) {
        this.createby = createby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.creattime
     *
     * @return the value of point_good.creattime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.creattime
     *
     * @param creattime the value for point_good.creattime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.updateby
     *
     * @return the value of point_good.updateby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getUpdateby() {
        return updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.updateby
     *
     * @param updateby the value for point_good.updateby
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setUpdateby(Integer updateby) {
        this.updateby = updateby;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.updatetime
     *
     * @return the value of point_good.updatetime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.updatetime
     *
     * @param updatetime the value for point_good.updatetime
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.status
     *
     * @return the value of point_good.status
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.status
     *
     * @param status the value for point_good.status
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column point_good.count
     *
     * @return the value of point_good.count
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public Integer getCount() {
        return count;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column point_good.count
     *
     * @param count the value for point_good.count
     *
     * @mbg.generated Wed Aug 22 20:06:34 CST 2018
     */
    public void setCount(Integer count) {
        this.count = count;
    }
}