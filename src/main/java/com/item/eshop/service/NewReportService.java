package com.item.eshop.service;
import  com.item.eshop.model.report;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
public interface NewReportService {
   // public report test(int id);
    public List<report> selectDayReport(int report_type, int page, int num, int userId);
    public int Count(int type, int userId);
    public void InsertDayReport(Date yesterDay);
    public void InsertWeekOrYearReport(Date mon, Date sun, int type);
    public void InsertMonthReport(Date day1, Date day2);
//    public List<report> getAllReportByType(int type);

 public List<report> selectWeekGroupByGoodName(String goodname, int userId);
 public List<report> selectMonthGroupByGoodName(String goodname, int userid);
 public List<report> selectYearGroupByGoodName(String goodname, int userid);
 public List<report> selectDayGroupByGoodName(String goodname, int userid);
 public List<report> selectByGoodName(String goodname, int type, int page, int n, int userId);
 public List<report> selectByGoodNameNotLimit(String goodname, int type, int userId);




}
