package com.item.eshop.controller;

import com.item.eshop.mapper.GoodMapper;
import com.item.eshop.mapper.TradeGoodMapper;
import com.item.eshop.mapper.TradeMapper;
import com.item.eshop.model.Trade;
import com.item.eshop.service.NewReportService;
import com.item.eshop.util.DateUtil;
import com.item.eshop.util.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.item.eshop.mapper.NewReportMapper;

import javax.servlet.http.HttpSession;

@Api(value = "ReportController", description = "报表crud等访问接口")
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    NewReportService reportService;
    @Autowired
    GoodMapper GoodMapper;
    @Autowired
    TradeMapper TradeMapper;
    @ApiOperation(value="根据type分页查询报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "report_type", value = "报表类型", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每页显示条数", required = true ,dataType = "Integer",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/getDayReport")
    public String getDayReport(HttpSession httpSession,@RequestParam(value = "report_type") Integer report_type, @RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectDayReport(report_type, (page - 1) * num, num,userId));
    }

    @ApiOperation(value = "根据type查询报表总数")
    @ApiImplicitParams({ @ApiImplicitParam(name = "report_type", value = "报表类型", required = true ,dataType = "Integer",paramType = "query")})
    @Transactional
    @ResponseBody
    @GetMapping("/getAllDayReport")
    public String Count(@RequestParam(value = "report_type") int report_type,HttpSession httpSession) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.Count(report_type,userId));
    }


//    @ApiOperation(value = "根据type查询报表")
//    @ApiImplicitParams({})
//    @Transactional
//    @ResponseBody
//    @GetMapping("/getAllReportByType")
//    public String getAllReportByType(@RequestParam(value = "report_type") int report_type, @RequestParam(value = "GoodName") String GoodName) {
//        return "";
//    }

    @ApiOperation(value = "根据GoodName分页查询日报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "报表类型", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "n", value = "每页显示条数", required = true ,dataType = "Integer",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/getReportByGoodName")
    public String selectDayByGoodName(HttpSession HttpSession,@RequestParam(value = "GoodName") String GoodName,@RequestParam(value = "type") int  type,@RequestParam(value = "page") int page,@RequestParam(value = "n") int n) {
        int userId=(int)HttpSession.getAttribute("user_id");

        return JsonObject.toJson(reportService.selectByGoodName(GoodName,type,(page-1)*n,n,userId));
    }
    @ApiOperation(value = "根据GoodName查询日报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "type", value = "报表类型", required = true ,dataType = "Integer",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/selectByGoodNameNotLimit")
    public String   selectByGoodNameNotLimit(HttpSession HttpSession,@RequestParam(value = "GoodName") String GoodName,@RequestParam(value = "type") int  type) {
        int userId=(int)HttpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectByGoodNameNotLimit(GoodName,type,userId));
    }


    @ApiOperation(value = "分组查询日报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/selectDayGroupByGoodName")
    public String selectDayGroupByGoodName(@RequestParam(value = "GoodName") String GoodName,HttpSession httpSession) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectDayGroupByGoodName(GoodName,userId));
    }



    @ApiOperation(value = "分组查询周报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/selectWeekGroupByGoodName")
    public String selectWeekGroupByGoodName(@RequestParam(value = "GoodName") String GoodName,HttpSession httpSession) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectWeekGroupByGoodName(GoodName,userId));
    }


    @ApiOperation(value = "分组查询月报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/selectMonthGroupByGoodName")
    public String selectMonthGroupByGoodName(@RequestParam(value = "GoodName") String GoodName,HttpSession httpSession) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectMonthGroupByGoodName(GoodName,userId));
    }



    @ApiOperation(value = "分组查询年报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "GoodName", value = "商品名称", required = true ,dataType = "String",paramType = "query")
    })
    @Transactional
    @ResponseBody
    @GetMapping("/selectYearGroupByGoodName")
    public String selectYearGroupByGoodName(@RequestParam(value = "GoodName") String GoodName,HttpSession httpSession) {
        int userId=(int)httpSession.getAttribute("user_id");
        return JsonObject.toJson(reportService.selectYearGroupByGoodName(GoodName,userId));
    }
//
//    @ApiOperation(value="测试产生日报表")
//    @ApiImplicitParams({})
//    @Transactional
//    @GetMapping("/data")
//    public void  data( ) {
//      //      产生月报表
// Calendar cal=Calendar.getInstance();
//        cal.set(2018,4,1);
//    Date date1=cal.getTime();
//    Calendar cal1=Calendar.getInstance();
//        cal1.set(2018,5,30);
//    Date date2=cal1.getTime();
//       for(int i=0;i<65;i++){
//        if(DateUtil.sameDate(date1,DateUtil.getMonthFirstDay(date1))){
//            Date sun=DateUtil.getMonthLastDay(date1);
//            reportService.InsertMonthReport(date1,sun);
//        }
//        cal.add(Calendar.DATE,1);
//        date1=cal.getTime();
//    }
//
//    }
//    @ApiOperation(value="产生报表数据")
//    @ApiImplicitParams({})
//    @Transactional
//    @GetMapping("/getreport")
//    public void getreport(){
//    //年报表
//        Calendar cal=Calendar.getInstance();
//        cal.set(2018,0,1);
//        Date date=cal.getTime();
//        Calendar cal1=Calendar.getInstance();
//       // reportService.InsertWeekOrYearReport(date,DateUtil.getYearLastDay(date),4);
//    }

}
    //produce daily data
//    Calendar cal=Calendar.getInstance();
//        cal.set(2018,4,1);
//                Date date=cal.getTime();
//                Calendar cal2=Calendar.getInstance();
//                cal2.set(2018,6,1);
//                Date date2=cal2.getTime();
//                for(int i=0;i<65;i++){
//        if(date.equals(date2))return;
//        else{
//        reportService.InsertDayReport(date);
//        }
//        cal.add(Calendar.DATE,1);
//        date=cal.getTime();
//        }
    // 产生周报表
//    List<Trade> userList=TradeMapper.getDayReportUserIdNum();
//    Calendar cal=Calendar.getInstance();
//        cal.set(2018,4,1);
//                Date date1=cal.getTime();
//                Calendar cal1=Calendar.getInstance();
//                cal1.set(2018,5,30);
//                Date date2=cal1.getTime();
//                for(int i=0;i<65;i++){
//        if(DateUtil.isRightWeek(date1)){
//        Calendar  c=Calendar.getInstance();
//        c.setTime(date1);
//        c.add(Calendar.DATE,7);
//        Date sun=c.getTime();
//        reportService.InsertWeekOrYearReport(date1,sun,2);
//
//        }
//        cal.add(Calendar.DATE,1);
//        date1=cal.getTime();
//        }

//    Calendar cal=Calendar.getInstance();
//        cal.set(2018,4,1);
//                Date date1=cal.getTime();
//                Calendar cal1=Calendar.getInstance();
//                cal1.set(2018,5,30);
//                Date date2=cal1.getTime();
//                for(int i=0;i<65;i++){
//        if(DateUtil.sameDate(date1,DateUtil.getMonthFirstDay(date1))){
//        Date sun=DateUtil.getMonthLastDay(date1);
//        reportService.InsertMonthReport(date1,sun);
//        }
//        cal.add(Calendar.DATE,1);
//        date1=cal.getTime();
//        }
//    @ApiOperation(value="产生报表数据")
//    @ApiImplicitParams({})
//    @Transactional
//    @GetMapping("/getreport")
//    public void getreport(){
//    //年报表
//        Calendar cal=Calendar.getInstance();
//        cal.set(2018,0,1);
//        Date date=cal.getTime();
//        Calendar cal1=Calendar.getInstance();
//        reportService.InsertWeekOrYearReport(date,DateUtil.getYearLastDay(date),4);
//
//        }
//
//    }

//    @ApiOperation(value="测试产生月报表")
//    @ApiImplicitParams({})
//    @Transactiona
//    @GetMapping("/test3")
//    public void  test3() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2018, 5, 1);
//        Date date = DateUtil.getBeforeMonthFirstDay(cal.getTime());
//        Date date2=DateUtil.getBeforeMonthLastDay(cal.getTime());
//        reportService.InsertMonthReport(date,date2 );
//
//    }
//    @ApiOperation(value="测试产生周报表")
//    @ApiImplicitParams({})
//    @Transactional
//    @GetMapping("/test2")
//    public void  test2() {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2018, 4, 7);
//        Date date = cal.getTime();
//        cal.setTime(date);
//        cal.add(Calendar.DAY_OF_WEEK,7);
//        Date date2=cal.getTime();
//        reportService.InsertWeekOrYearReport(date,date2,2 );
//
//    }
//@ApiOperation(value="测试产生日报表")
//@ApiImplicitParams({})
//@Transactional
//@GetMapping("/test")
//public void  test() {
//        Calendar cal=Calendar.getInstance();
//        cal.set(2018,4,10);
//        Date date=cal.getTime();
//        reportService.InsertDayReport(date);
//
//        }
//    @ApiOperation(value="测试产生年报表")
//    @ApiImplicitParams({})
//    @Transactional
//    @GetMapping("/test4")
//    public void  test4() {
//        Calendar cal=Calendar.getInstance();
//        cal.set(2019,0,1);
//        Date date=cal.getTime();
//        reportService.InsertWeekOrYearReport(DateUtil.getBeforeYearFirstDay(date),DateUtil.getBeforeYearLastDay(date),4);
//
//    }

