package com.item.eshop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class report implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.id
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private BigDecimal tradeTotal;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_max
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private BigDecimal tradeMax;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_time
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer tradeTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.sell_number
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer sellNumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_paying
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer tradePaying;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_delivering
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer tradeDelivering;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_fail
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer tradeFail;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_success
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer tradeSuccess;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.diff
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private BigDecimal diff;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.goodname
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private String goodname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.counts
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer counts;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.price
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private BigDecimal price;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private BigDecimal total;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.report_type
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer reportType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.starttime
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Date starttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column report.trade_id
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    private Integer userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.id
     *
     * @return the value of report.id
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.id
     *
     * @param id the value for report.id
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_total
     *
     * @return the value of report.trade_total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public BigDecimal getTradeTotal() {
        return tradeTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_total
     *
     * @param tradeTotal the value for report.trade_total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeTotal(BigDecimal tradeTotal) {
        this.tradeTotal = tradeTotal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_max
     *
     * @return the value of report.trade_max
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public BigDecimal getTradeMax() {
        return tradeMax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_max
     *
     * @param tradeMax the value for report.trade_max
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeMax(BigDecimal tradeMax) {
        this.tradeMax = tradeMax;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_time
     *
     * @return the value of report.trade_time
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getTradeTime() {
        return tradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_time
     *
     * @param tradeTime the value for report.trade_time
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeTime(Integer tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.sell_number
     *
     * @return the value of report.sell_number
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getSellNumber() {
        return sellNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.sell_number
     *
     * @param sellNumber the value for report.sell_number
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setSellNumber(Integer sellNumber) {
        this.sellNumber = sellNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_paying
     *
     * @return the value of report.trade_paying
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getTradePaying() {
        return tradePaying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_paying
     *
     * @param tradePaying the value for report.trade_paying
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradePaying(Integer tradePaying) {
        this.tradePaying = tradePaying;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_delivering
     *
     * @return the value of report.trade_delivering
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getTradeDelivering() {
        return tradeDelivering;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_delivering
     *
     * @param tradeDelivering the value for report.trade_delivering
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeDelivering(Integer tradeDelivering) {
        this.tradeDelivering = tradeDelivering;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_fail
     *
     * @return the value of report.trade_fail
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getTradeFail() {
        return tradeFail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_fail
     *
     * @param tradeFail the value for report.trade_fail
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeFail(Integer tradeFail) {
        this.tradeFail = tradeFail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_success
     *
     * @return the value of report.trade_success
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getTradeSuccess() {
        return tradeSuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_success
     *
     * @param tradeSuccess the value for report.trade_success
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTradeSuccess(Integer tradeSuccess) {
        this.tradeSuccess = tradeSuccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.diff
     *
     * @return the value of report.diff
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public BigDecimal getDiff() {
        return diff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.diff
     *
     * @param diff the value for report.diff
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setDiff(BigDecimal diff) {
        this.diff = diff;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.goodname
     *
     * @return the value of report.goodname
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public String getGoodname() {
        return goodname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.goodname
     *
     * @param goodname the value for report.goodname
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setGoodname(String goodname) {
        this.goodname = goodname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.counts
     *
     * @return the value of report.counts
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getCounts() {
        return counts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.counts
     *
     * @param counts the value for report.counts
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.price
     *
     * @return the value of report.price
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.price
     *
     * @param price the value for report.price
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.total
     *
     * @return the value of report.total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.total
     *
     * @param total the value for report.total
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.report_type
     *
     * @return the value of report.report_type
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getReportType() {
        return reportType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.report_type
     *
     * @param reportType the value for report.report_type
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.starttime
     *
     * @return the value of report.starttime
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.starttime
     *
     * @param starttime the value for report.starttime
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column report.trade_id
     *
     * @return the value of report.trade_id
     *
     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column report.trade_id
     *
     * @param tradeId the value for report.trade_id
     *

     * @mbg.generated Tue Jul 10 11:08:37 CST 2018
     */

}