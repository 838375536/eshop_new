package com.item.eshop.controller;

import com.item.eshop.mapper.GoodCategoryMapper;
import com.item.eshop.model.GoodCategory;
import com.item.eshop.service.GoodCategoryService;
import com.item.eshop.util.GetShop;
import com.item.eshop.util.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Api(value = "GoodCategoryController", description = "商品类型的增删改查等访问接口")
@RestController
@RequestMapping("/category")
public class GoodCategoryController {

    @Autowired
    GoodCategoryService goodCategoryService;

    @Autowired
    GoodCategoryMapper goodCategoryMapper;

    @ApiOperation(value="(app端) 根据id获取商品分类对象",notes="返回值GoodCategory对象 或 null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品类型id", required = true ,dataType = "Integer",paramType = "query")
    })
    @PostMapping("/findById")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(goodCategoryService.selectByPrimaryKey(id));
    }

    @ApiOperation(value="(app端) 根据商铺id获取所有商品分类对象",notes="返回值List<GoodCategory>对象 或 null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "商品类型sid", required = true ,dataType = "Integer",paramType = "query")
    })
    @PostMapping("/findMore")
    public String findMore(@RequestParam(value = "shopId",required = false)Integer sid,HttpSession httpSession) {
        Integer shopId = GetShop.get(sid,httpSession);
        return JsonObject.toJson(goodCategoryService.selectMore(shopId));
    }

    @ApiOperation(value="(app端) 根据商铺id以排序的方式获取所有商品分类对象",notes="返回值List<GoodCategory>对象 或 null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sid", value = "商品类型sid", required = true ,dataType = "Integer",paramType = "query")
    })
    @PostMapping("/findBySort")
    public String findByValid(@RequestParam(value = "shopId",required = false)Integer sid,HttpSession httpSession) {
        Integer shopId = GetShop.get(sid,httpSession);
        return JsonObject.toJson(goodCategoryService.selectMoreBySort(shopId));
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    @ApiOperation(value="(后台端) 添加商品分类类型",notes="返回值(int):1:支付成功，0：支付失败。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品类型名", required = true ,dataType = "String",paramType = "query")
    })
    @PostMapping("/add")
    public int add(@RequestParam(value = "name")String name,HttpSession httpSession) {
        int shopId = Integer.parseInt(httpSession.getAttribute("shopId").toString());
        GoodCategory goodCategory = new GoodCategory();
        int sort_no = goodCategoryMapper.selectCountNum(shopId);
        goodCategory.setId(sort_no+1);
        goodCategory.setSort_no(sort_no+1);
        goodCategory.setShop_id(shopId);
        goodCategory.setName(name);
        return goodCategoryService.insert(goodCategory);
    }

    @ApiOperation(value="(后台端) 删除商品分类类型",notes="返回值(int):1:支付成功，0：支付失败。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商品类型名", required = true ,dataType = "String",paramType = "query")
    })
    @PostMapping("/delete")
    public int delete(@RequestParam(value = "id")Integer id) {
        return goodCategoryService.deleteByPrimaryKey(id);
    }

    @ApiOperation(value="(后台端) 更新商品分类类型",notes="返回值(int):1:支付成功，0：支付失败。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品类型id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "商品类型名", required = true ,dataType = "String",paramType = "query")
    })
    @PostMapping("/updateName")
    public int updateName(@RequestParam(value = "name")String name,@RequestParam(value = "id")Integer id) {
        GoodCategory goodCategory = new GoodCategory();
        goodCategory.setId(id);
        goodCategory.setName(name);
        return goodCategoryService.updateByPrimaryKeySelective(goodCategory);
    }

    @ApiOperation(value="(后台端) 修改商品分类排序位置",notes="返回值(int):1:支付成功，0：支付失败。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品类型id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "no", value = "商品类型排序位置号", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "move", value = "商品类型排序移动位置数", required = true ,dataType = "Integer",paramType = "query")
    })
    @PostMapping("/updateSort")
    public int updateSort(@RequestParam(value = "no")int no,@RequestParam(value = "move")int move,@RequestParam(value = "id")Integer id) {
        int result = goodCategoryMapper.updateBySort(no,no+move);
        if(result==1){
            return goodCategoryMapper.updateById(no+move,id);
        }
        return 0;
    }


}
