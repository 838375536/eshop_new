package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.*;
import com.item.eshop.model.Good;
import com.item.eshop.model.Trade;
import com.item.eshop.model.TradeGood;
import com.item.eshop.service.NewReportService;
import com.item.eshop.util.DateUtil;
import org.mapstruct.BeforeMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.item.eshop.model.report;
import sun.awt.geom.AreaOp;

import java.math.BigDecimal;
import java.util.*;


@Service
public class NewReportServiceImpl implements NewReportService {
    @Autowired
    NewReportMapper reportMapper;
    @Autowired
    TradeGoodMapper tradeGoodMapper;
    @Autowired
    TradeMapper tradeMapper;
    @Autowired
    GoodMapper goodMapper;

    //    public report test(int id){
//        return reportMapper.selectByPrimaryKey(id);
//    }
    public List<report> getAllReportByType(int type){return reportMapper.getAllReportByType(type);}
    public List<report> selectDayReport(int report_type, int page, int num,int userId) {
        return reportMapper.selectDayReport(report_type, page, num,userId);
    }

    public int Count(int type,int userId) {
        return reportMapper.Count(type,userId);
    }
    public List<report> selectDayGroupByGoodName(String goodname,int userId){return  reportMapper.selectDayGroupByGoodName(goodname,userId);}
    public List<report> selectWeekGroupByGoodName(String goodname,int userId){return  reportMapper.selectWeekGroupByGoodName(goodname, userId);}
    public List<report> selectMonthGroupByGoodName(String goodname,int userId){return  reportMapper.selectMonthGroupByGoodName(goodname, userId);}
    public List<report> selectYearGroupByGoodName(String goodname,int userId){return  reportMapper.selectYearGroupByGoodName(goodname, userId);}
    public void InsertDayReport(Date yesterDay) {
            BigDecimal tradeTotal=new BigDecimal(0);
            int  tradeTime=0;
            int sellNumber=0;
            int deliver=tradeMapper.selectDeliverTradeByTime(yesterDay)==null?0:tradeMapper.selectDeliverTradeByTime(yesterDay).size();
            int fail=tradeMapper.selectFialTradeByTime(yesterDay)==null?0:tradeMapper.selectFialTradeByTime(yesterDay).size();
            int paying=tradeMapper.selectPayingTradeByTime(yesterDay)==null?0:tradeMapper.selectPayingTradeByTime(yesterDay).size();
            BigDecimal max=new BigDecimal(0);
            List<Trade> tradeList=tradeMapper.selectAllByTime(yesterDay);

            if(tradeList!=null&&tradeList.size()>0){
                BigDecimal money[]=new BigDecimal[tradeList.size()];
                for(int i=0;i<tradeList.size();i++){
                    Trade trade=tradeList.get(i);
                    money[i]=trade.getFactAmount();
                    tradeTotal=tradeTotal.add(trade.getFactAmount());
                    sellNumber=sellNumber+trade.getTrade_Good().getCount();
                }
                Arrays.sort(money);
                max=money[tradeList.size()-1];
                List<Trade> RealNum= tradeMapper.getDayReportNum(yesterDay);
                if(RealNum!=null&&RealNum.size()>0){
                    for(int i=0;i<RealNum.size();i++){
                        Trade trade=RealNum.get(i);
                        report report=new report();
                        report.setTradeTotal(tradeTotal);
                        report.setSellNumber(sellNumber);
                        report.setTradeDelivering(deliver);
                        report.setTradeTime(tradeList.size());
                        report.setTradeFail(fail);
                        report.setTradePaying(paying);
                        report.setTradeMax(max);
                        report.setUserId(trade.getUserId()==null?0:trade.getUserId());
                        Good good=goodMapper.selectByPrimaryKey(trade.getTrade_Good().getGoodId());
                        report.setGoodname(good==null?"商品已删除":good.getName());
                        BigDecimal price=good==null?new BigDecimal(0):good.getPrice();
                        report.setCounts(trade.getTrade_Good().getCount());
                        report.setTotal(price.multiply(new BigDecimal(trade.getTrade_Good().getCount())));
                        report.setReportType(1);
                        report.setPrice(price);
                        Calendar cal=Calendar.getInstance();
                        cal.setTime(yesterDay);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.HOUR_OF_DAY, 0);
                        report.setStarttime(cal.getTime());
                        reportMapper.insert(report);
                    }
                }
        }
    }

    public void InsertWeekOrYearReport(Date mon, Date sun,int reporttype) {
            List<report> reports=reportMapper.selectDayReportBetweenWeek(mon,sun);
            if(reports!=null&&reports.size()>0){
                List<report> report=reportMapper.selectSumBetween(mon,sun);
               if(report!=null&&report.size()>0){
                   for(int j=0;j<report.size();j++){

                       int  tradeTime=report.get(j).getTradeTime();
                       int sellNumber=report.get(j).getSellNumber();
                       int deliver=report.get(j).getTradeDelivering();
                       int fail=report.get(j).getTradeFail();
                       int paying=report.get(j).getTradePaying();
                       BigDecimal tradeTotal=report.get(j).getTradeTotal();
                       BigDecimal max=reportMapper.selectMaxBetween(mon,sun,report.get(j).getUserId()).getTradeMax();
                       List<report> RealNum= reportMapper.selectRealNumBetween(mon,sun,report.get(j).getUserId());

                       if(RealNum!=null&&RealNum.size()>0){

                           for(int i=0;i<RealNum.size();i++){
                               report  a= RealNum.get(i);
                               report report1=new report();
                               report1.setTradeTotal(tradeTotal);
                               report1.setSellNumber(sellNumber);
                               report1.setTradeDelivering(deliver);
                               report1.setTradeTime(tradeTime);
                               report1.setTradeFail(fail);
                               report1.setTradePaying(paying);
                               report1.setTradeMax(max);
                               report1.setGoodname(a.getGoodname());
                               report1.setPrice(a.getPrice());
                               report1.setTotal(a.getTotal());
                               report1.setReportType(reporttype);
                                report1.setUserId(a.getUserId());
                               Calendar cal=Calendar.getInstance();
                               report1.setCounts(a.getCounts());
                               cal.setTime(mon);
                               cal.set(Calendar.MINUTE, 0);
                               cal.set(Calendar.SECOND, 0);
                               cal.set(Calendar.HOUR_OF_DAY, 0);
                               report1.setStarttime(cal.getTime());
                               reportMapper.insert(report1);
                           }
                       }
                   }
               }
            }
    }

    public void InsertMonthReport(Date mon, Date sun) {
        BigDecimal BeforTradeTotal=new BigDecimal(0);
        List<report> reports=reportMapper.selectDayReportBetweenWeek(mon,sun);
        if(reports!=null&&reports.size()>0){
            List<report> report=reportMapper.selectSumBetween(mon,sun);
           if(report!=null&&report.size()>0){
               for(int j=0;j<report.size();j++){
                   if(reports!=null&&reports.size()>0){


                       int  tradeTime=report.get(j).getTradeTime();
                       int sellNumber=report.get(j).getSellNumber();
                       int deliver=report.get(j).getTradeDelivering();
                       int fail=report.get(j).getTradeFail();
                       int paying=report.get(j).getTradePaying();
                       BigDecimal tradeTotal=report.get(j).getTradeTotal();
                       BigDecimal max=reportMapper.selectMaxBetween(mon,sun,report.get(j).getUserId()).getTradeMax();
                       List<report> RealNum= reportMapper.selectRealNumBetween(mon,sun,report.get(j).getUserId());
                       if(RealNum!=null&&RealNum.size()>0){



                           for(int i=0;i<RealNum.size();i++){
                               report  a= RealNum.get(i);
                               report report1=new report();

                               report diffReport= reportMapper.selectBeforeMonthReportByTime(DateUtil.getMonthFirstDay(a.getStarttime()),3,a.getGoodname(),a.getUserId());
                               BigDecimal diff=diffReport==null?new BigDecimal(0):tradeTotal.subtract(diffReport.getDiff());
                               report1.setTradeTotal(tradeTotal);
                               report1.setSellNumber(sellNumber);
                               report1.setTradeDelivering(deliver);
                               report1.setTradeTime(tradeTime);
                               report1.setTradeFail(fail);
                               report1.setTradePaying(paying);
                               report1.setTradeMax(max);
                               report1.setGoodname(a.getGoodname());
                               report1.setPrice(a.getPrice());
                               report1.setTotal(a.getTotal());
                               report1.setReportType(3);
                               report1.setDiff(diff);
                               Calendar cal=Calendar.getInstance();
                               report1.setCounts(a.getCounts());
                               report1.setUserId(a.getUserId());
                               cal.setTime(mon);
                               cal.set(Calendar.MINUTE, 0);
                               cal.set(Calendar.SECOND, 0);
                               cal.set(Calendar.HOUR_OF_DAY, 0);
                               report1.setStarttime(cal.getTime());
                               reportMapper.insert(report1);
                           }
                       }
                   }
               }
           }
        }

    }
    public List<report> selectByGoodName(String goodname, int type, int  page, int  n,int userID){
        return reportMapper.selectByGoodName(goodname,type,page,n,userID);
    }
    public List<report> selectByGoodNameNotLimit(String goodname,int type,int  userId){
        return reportMapper.selectByGoodNameNotLimit(goodname,type,userId);
    }
}
