package com.item.eshop.controller;

import com.item.eshop.model.Coupon;
import com.item.eshop.service.CouponService;
import com.item.eshop.util.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

@Api(value = "CouponController", description = "（后台管理系统接口）优惠券增删改查等访问接口")
@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @ApiOperation(value = "(App端) 根据id查找优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券ID", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/find")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(couponService.selectByPrimaryKey(id));
    }


    // add :  chan  2018/4/4
    @ApiOperation(value = "(App端) 查找所有可用的优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "优惠券ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "优惠券ID", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/findMore")
    public String findMore(@RequestParam(value = "page",required = false)Integer page,@RequestParam(value = "num",required = false)Integer num) {
        int p = 0;
        int n = 100;
        if(page!=null){
            p = page;
        }
        if(num!=null){
            n = num;
        }
        return JsonObject.toJson(couponService.selectMore((p-1)*n+1,n));
    }

    // ======================  admin background management system interface ( user interceptor) =============================
    @ApiOperation(value = "(后台) 添加优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "total", value = "优惠起步价", required = true ,dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "minus", value = "优惠价", required = true ,dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "count", value = "数量", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "day", value = "有效期", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/add")
    public int add(@RequestParam(value = "total")double total,@RequestParam(value = "minus")double minus,@RequestParam(value = "count")Integer count,@RequestParam(value = "day")Integer day) {
        Coupon c = new Coupon();
        c.setCount(count);
        c.setDay(day);
        c.setValid(1);
        c.setMinus(new BigDecimal(minus));
        c.setTotal(new BigDecimal(total));
        return couponService.insertSelective(c);
    }

    @ApiOperation(value = "(后台) 删除优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/delete")
    public int delete(@RequestParam(value = "id")Integer id) {
        return couponService.deleteByPrimaryKey(id);
    }

    @ApiOperation(value = "(后台) 更新优惠券优惠起步价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "total", value = "优惠起步价", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/update")
    public int update(@RequestParam(value = "id")Integer id,@RequestParam(value = "total")double total) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setTotal(new BigDecimal(total));
        return couponService.updateByPrimaryKeySelective(coupon);
    }

    @ApiOperation(value = "(后台) 更新优惠券优惠起步价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "total", value = "优惠起步价", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "minus", value = "优惠金额", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/update")
    public int update(@RequestParam(value = "id")Integer id,@RequestParam(value = "total")double total,@RequestParam(value = "minus")double minus) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setTotal(new BigDecimal(total));
        coupon.setMinus(new BigDecimal(minus));
        return couponService.updateByPrimaryKeySelective(coupon);
    }

    @ApiOperation(value = "(后台) 更新优惠券有效状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "valid", value = "优惠券状态：1有效，-1无效，0删除", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/updateValid")
    public int updateValid(@RequestParam(value = "id")Integer id,@RequestParam(value = "valid")int valid) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setValid(valid);
        return couponService.updateByPrimaryKeySelective(coupon);
    }


    //取消该规则接口
    @ResponseBody
    @PostMapping("/addRules")
    public int addRules(@RequestParam(value = "total")double total,@RequestParam(value = "minus")double minus) {
        Coupon c = new Coupon();
        c.setCount(0);   //type
        c.setValid(10);
        c.setMinus(new BigDecimal(minus));
        c.setTotal(new BigDecimal(total));
        return couponService.insertSelective(c);
    }

    //取消该规则接口
    @ResponseBody
    @PostMapping("/deleteRules")
    public int deleteRules(@RequestParam("id")int id) {
        return couponService.deleteByPrimaryKey(id);
    }

    //取消该规则接口
    @ResponseBody
    @PostMapping("/showRules")
    public String showRules() {
        return JsonObject.toJson(couponService.selectRule());
    }

}
