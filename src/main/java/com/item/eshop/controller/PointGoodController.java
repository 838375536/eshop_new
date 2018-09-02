package com.item.eshop.controller;

import com.item.eshop.config.Constant;
import com.item.eshop.model.PointGood;
import com.item.eshop.service.PointGoodService;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.UploadImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Api(value = "PointGoodController", description = "积分商品访问接口")
@RestController
@RequestMapping("//pointgood")
public class PointGoodController {
    @Autowired
    Constant constant;
    @Autowired
    PointGoodService PointGoodService;
    @ResponseBody
    @GetMapping("/getAllGoodLimit")
    @ApiOperation(value="查询所有积分物品 （后台管理员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "物品名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "状态", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "需要返回的记录数", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public String getAllGoodLimit(@RequestParam(value = "name")String name,@RequestParam(value = "status")int status,@RequestParam(value = "page")Integer page, @RequestParam(value = "num")Integer num) {

        return JsonObject.toJson(PointGoodService.selectByQueryLimit(name,status,(page-1)*num,num));
    }
    @ResponseBody
    @GetMapping("/getAllGood")
    @ApiOperation(value="分页查询所有积分物品 （后台管理员）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "物品名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "状态", required = true ,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public String getAllGood(@RequestParam(value = "name")String name,@RequestParam(value = "status")int status) {

        return JsonObject.toJson(PointGoodService.selectByQuery(name,status));
    }

    @ResponseBody
    @GetMapping("/getById")
    @ApiOperation(value="根据Ｉｄ查询积分物品 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true ,dataType = "Integer",paramType = "query"),

            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public String getById(@RequestParam(value = "id")int id) {

        return JsonObject.toJson(PointGoodService.getById(id));
    }


    @PostMapping("/getupdateById")
    @ApiOperation(value="update积分物品status ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "status", required = true ,dataType = "Integer",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public void updateById(@RequestParam(value = "id")int id,@RequestParam(value = "status")int status) {
        try{
            PointGood pointGood=PointGoodService.getById(id);
            pointGood.setStatus(status);
            PointGoodService.updateById(pointGood);
        }catch (Exception e){

        }
    }
    @PostMapping("/updatePointGood")
    @ApiOperation(value="update积分物品 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "name", required = true ,dataType = "String",paramType = "query"),
            //  @ApiImplicitParam(name = "updatetime", value = "updatetime", required = true ,dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name = "point", value = "point", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "image", value = "image", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "count", value = "count", required = true ,dataType = "Integer",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public void updatePointGood(@RequestParam(value = "id")int id,
                                @RequestParam(value = "goodname")String  goodname,
                                @RequestParam(value = "point")int point,
                                @RequestParam(value = "image",required = false)MultipartFile  image,
                                @RequestParam(value = "count")int count, HttpSession httpSession) {
        PointGood pointGood=new PointGood();
        int UserId=(int)httpSession.getAttribute("user_id");
        pointGood.setId(id);
        pointGood.setUpdatetime(new Date());
        pointGood.setUpdateby(UserId);
        pointGood.setPoint(point);
        if(image!=null){
            String url = new UploadImage().uploadImage(image,0,constant.getPath(),constant.getUrl());
            pointGood.setImage(url);
        }
        pointGood.setCount(count);
        pointGood.setName(goodname);
        PointGoodService.updateById(pointGood);
    }
    @PostMapping("/addPointGood")
    @ApiOperation(value="add 积分物品 ")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "name", value = "name", required = true ,dataType = "String",paramType = "query"),
            //   @ApiImplicitParam(name = "creattime", value = "creattime", required = true ,dataType = "Date",paramType = "query"),
            @ApiImplicitParam(name = "point", value = "point", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "image", value = "image", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "count", value = "count", required = true ,dataType = "Integer",paramType = "query"),
    })
    public void addPointGood(
            @RequestParam(value = "goodname")String  goodname,
            @RequestParam(value = "point")int point,
            @RequestParam(value = "image",required = false)MultipartFile image,
            @RequestParam(value = "count")int count, HttpSession httpSession) {
        PointGood pointGood=new PointGood();
        int UserId=(int)httpSession.getAttribute("user_id");
        pointGood.setCreattime(new Date());
        pointGood.setCreateby(UserId);
        pointGood.setStatus(0);
        pointGood.setPoint(point);
        if(image!=null){
            String url = new UploadImage().uploadImage(image,0,constant.getPath(),constant.getUrl());
            pointGood.setImage(url);
        }
        pointGood.setCount(count);
        pointGood.setName(goodname);
        PointGoodService.addPointGood(pointGood);
    }
    @ResponseBody
    @PostMapping("/deleteById")
    @ApiOperation(value="根据Ｉｄ delete积分物品 ")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true ,dataType = "Integer",paramType = "query"),

            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public void deleteById(@RequestParam(value = "id")int id) {

        PointGoodService.deleteById(id);
    }
}
