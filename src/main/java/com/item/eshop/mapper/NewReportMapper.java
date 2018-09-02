package com.item.eshop.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import  com.item.eshop.model.report;

import java.util.Date;
import java.util.List;
@Repository
public interface NewReportMapper {
   public report selectByPrimaryKey(int id);
   public List<report> selectDayReport(int reporttype, int page, int num, int userId);
   public int Count(int type, int userId);
   public  void insert(report report);
   public  List<report> selectDayReportBetweenWeek(Date mon, Date sun);
   public report selectBeforeMonthReportByTime(@Param("date") Date date, @Param("type") int type, @Param("goodname") String goodId, @Param("userId") int userId);
//   public List<report>  selectReportByTime(Date date1,Date date2 );
   public report selectMaxBetween(@Param("date1") Date date1, @Param("date2") Date date2, @Param("userId") int id);
   public  List<report> selectSumBetween(Date day1, Date day2);
   public List<report> selectRealNumBetween(@Param("date1") Date day1, @Param("date2") Date day2, @Param("userId") int id);
   public List<report> getAllReportByType(int type);

   public List<report> selectDayGroupByGoodName(@Param("goodname") String goodname, @Param("userId") int userId);
   public List<report> selectWeekGroupByGoodName(@Param("goodname") String goodname, @Param("userId") int userId);
   public List<report> selectMonthGroupByGoodName(@Param("goodname") String goodname, @Param("userId") int userId);
   public List<report> selectYearGroupByGoodName(@Param("goodname") String goodname, @Param("userId") int userId);
  //  public    List<report> selectSumFromDay(Date date1,Date date2);
    public List<report> selectByGoodName(@Param("goodname") String goodname, @Param("type") int type, @Param("page") int page, @Param("n") int n, @Param("userId") int userId);

   public List<report> selectByGoodNameNotLimit(@Param("goodname") String goodname, @Param("type") int type, @Param("userId") int userId);
 }
