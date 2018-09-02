package com.item.eshop.controller;
import com.item.eshop.config.Constant;
import com.item.eshop.mapper.UserMapper;
import com.item.eshop.model.*;
import com.item.eshop.service.ShopClassification;
import com.item.eshop.service.ShopService;
import com.item.eshop.service.UserService;
import com.item.eshop.util.GetShop;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.UploadImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(value= "ShopController", description = "（后台管理系统接口）商铺管理等访问接口")
@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    Constant constant;

    @Autowired
    ShopClassification shopClassification;

    @Autowired
    ShopService shopService;

    @Autowired
    UserMapper userMapper;

    public void setShopClassification(ShopClassification shopClassification) {
        this.shopClassification = shopClassification;
    }

    // ======================  admin background management system interface ( user interceptor) =============================
    @ResponseBody
    @PostMapping("/add")
    @ApiOperation(value="添加shop商铺（后台）",notes="返回值int 1:成功 0:失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商铺名", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "商铺介绍", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "contact", value = "联系方式", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "school_id", value = "学校id", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "address", value = "地址", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 1:营业 0:休息", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "range", value = "配送范围", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "gps", value = "gps定位", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "shop_type", value = "商铺类型", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "start_time", value = "开店时间", required = true ,dataType = "time",paramType = "query"),
            @ApiImplicitParam(name = "end_time", value = "关店时间", required = true ,dataType = "time",paramType = "query"),
            @ApiImplicitParam(name = "shop_other", value = "商铺补充", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "账号", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "min_price", value = "配送价", required = true ,dataType = "int",paramType = "query")
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query")
    })
    @Transactional
    public int add(@RequestParam(value = "name")String name,
                   @RequestParam(value = "introduce",required = false)String introduce,
                   @RequestParam(value = "contact")String contact,
                   @RequestParam(value = "school_id")Integer school_id,
                   @RequestParam(value = "address")String address,
                   @RequestParam(value = "image")MultipartFile image,
                   @RequestParam(value = "status")Integer status,
                   @RequestParam(value = "range")Integer range,
                   @RequestParam(value = "gps",required = false)String gps,
                   @RequestParam(value = "shop_type")String shop_type,
                   @RequestParam(value = "shop_other",required = false)String shop_other,
                   @RequestParam(value = "start_time")String start_time,
                   @RequestParam(value = "end_time")String end_time,
                   @RequestParam(value = "phone")String phone,
                   @RequestParam(value = "min_price")Integer min_price,
                   @RequestParam(value = "password")String password,
                    HttpSession httpSession) {
        //int user_id = (int) httpSession.getAttribute("user_id");
        Shop shop = new Shop();
        //shop.setUser_id(user_id);
        shop.setShop_name(name);
        shop.setIntroduce(introduce);
        shop.setStatus(status);
        shop.setImage(null);
        shop.setSchool_id(school_id);
        shop.setContact(contact);
        shop.setAddress(address);
        shop.setGps(gps);
        shop.setMin_price(min_price);
        shop.setDelivery_range(range);
        shop.setShop_type(shop_type);
        shop.setShop_other(shop_other);
        shop.setStart_time(start_time.trim());
        shop.setEnd_time(end_time.trim());
        if(image!=null) {
            String url = new UploadImage().uploadImage(image, 4, constant.getPath(), constant.getUrl());
            shop.setImage(url);
        }
       // shop.setStart_time(start_time);
       // shop.setEnd_time(end_time);
        User user = new User();
        user.setPhone(phone);
        user.setPasswd(password);
        user.setStatus(8);
        User user2 = userMapper.selectByPhone(phone);
        if(user2!=null){
            return -1;
        }else {
            userMapper.insertSelective(user);
            user2 = userMapper.selectByPhone(phone);
            shop.setUser_id(user2.getId());
            System.out.println("shop:::::"+shop.getStart_time().length());
            return shopService.insertSelective(shop);
        }
    }

    @ResponseBody
    @PostMapping("/update")
    @ApiOperation(value="更改商铺信息（后台）",notes="返回值int 1:成功 0:失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商铺名", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "商铺介绍", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "contact", value = "联系方式", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "school_id", value = "学校id", required = false ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "address", value = "地址", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 1:营业 0:休息", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "range", value = "配送范围", required = false ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "gps", value = "gps定位", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "shop_type", value = "商铺类型", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "start_time", value = "开店时间", required = false ,dataType = "time",paramType = "query"),
            @ApiImplicitParam(name = "end_time", value = "关店时间", required = false ,dataType = "time",paramType = "query"),
            @ApiImplicitParam(name = "shop_other", value = "商铺补充", required = false ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "id", value = "商铺id", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "min_price", value = "配送价", required = true ,dataType = "int",paramType = "query")
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public int update(
                    @RequestParam(value = "id")Integer id,
                    @RequestParam(value = "name",required = false)String name,
                      @RequestParam(value = "introduce",required = false)String introduce,
                      @RequestParam(value = "contact",required = false)String contact,
                      @RequestParam(value = "school_id",required = false)Integer school_id,
                      @RequestParam(value = "address",required = false)String address,
                   @RequestParam(value = "image",required = false)MultipartFile image,
                      @RequestParam(value = "status",required = false)Integer status,
                      @RequestParam(value = "range",required = false)Integer range,
                      @RequestParam(value = "gps",required = false)String gps,
                      @RequestParam(value = "shop_type",required = false)String shop_type,
                      @RequestParam(value = "shop_other",required = false)String shop_other,
                      @RequestParam(value = "start_time",required = false)String start_time,
                      @RequestParam(value = "end_time",required = false)String end_time,
                        @RequestParam(value = "min_price")Integer min_price,
                   HttpSession httpSession) {
//        try {
//            int user_id = (int) httpSession.getAttribute("user_id");
//        }catch (NullPointerException npe){
//            return -1;
//        }

        Shop shop = new Shop();
        //shop.setUser_id(user_id);
        shop.setShop_name(name);
        shop.setIntroduce(introduce);
        shop.setStatus(status);
        shop.setImage(null);
        shop.setSchool_id(school_id);
        shop.setContact(contact);
        shop.setAddress(address);
        shop.setGps(gps);
        shop.setDelivery_range(range);
        shop.setShop_type(shop_type);
        shop.setShop_other(shop_other);
        if(start_time!=null)
        shop.setStart_time(start_time.trim());
        if(end_time!=null)
        shop.setEnd_time(end_time.trim());
        if(image!=null) {
            String url = new UploadImage().uploadImage(image, 4, constant.getPath(), constant.getUrl());
            shop.setImage(url);
        }
        shop.setId(id);
        //shop.setUser_id(user_id);
        return shopService.updateByPrimaryKeySelective(shop);
    }

    @ResponseBody
    @PostMapping("/delete")
    @ApiOperation(value="删除商铺（后台）",notes="返回值int 1:成功 0:失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商铺id", required = true ,dataType = "int",paramType = "query"),
    })
    public int delete(@RequestParam(value = "id")Integer id) {
        return shopService.deleteByPrimaryKey(id);
    }

    @ResponseBody
    @PostMapping("/findByCategory")
//    @ApiOperation(value="根据分类查找",notes="返回值int 1:成功 0:失败")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "name", value = "商铺名", required = true ,dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "introduce", value = "商铺介绍", required = true ,dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "contact", value = "联系方式", required = true ,dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "school_id", value = "学校id", required = true ,dataType = "int",paramType = "query"),
//            @ApiImplicitParam(name = "address", value = "地址", required = true ,dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "status", value = "状态 1:营业 0:休息", required = true ,dataType = "String",paramType = "query"),
//            @ApiImplicitParam(name = "id", value = "商铺id", required = true ,dataType = "int",paramType = "query"),
//            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
//    })
    public String findByCategory(@RequestParam(value = "category")Integer category,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(shopService.selectMoreByCategory(category,(page-1)*num,num));
    }

    @ResponseBody
    @PostMapping("/findByUserId")
    @ApiOperation(value="查询我的商铺信息（后台）",notes="商铺对象 json格式")
    public String findByUser(HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        return JsonObject.toJson(shopService.selectByUser(user_id));
    }

    // ======================  admin background management system interface ( user interceptor) =============================
    @ResponseBody
    @PostMapping("/findById")
    @ApiOperation(value="根据商铺id查询商铺信息（后台）",notes="商铺对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商铺id", required = true ,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(shopService.selectByPrimaryKey(id));
    }

    @ResponseBody
    // add :  chan  2018/4/4
    @PostMapping("/findMore")
    @ApiOperation(value="查询所有商铺 分页查询（后台管理员）",notes="商铺对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num", value = "需要返回的商铺记录数", required = true ,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "int",paramType = "query"),
            //@ApiImplicitParam(name = "image", value = "商铺图标", required = true ,dataType = "String",paramType = "query"),
    })
    public String findMore(@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(shopService.selectMore((page-1)*num,num));
    }

    //add zheng 2018-6-30
    @ApiOperation(value="查找所有省份（app）",notes="返回值(省份对象)数组或null")
    @ResponseBody
    @PostMapping("/getAllProvince")
    public String getAllProvince(){
        return JsonObject.toJson(shopClassification.getAllProvice());
    }

    @ResponseBody
    @PostMapping("/getSchoolByProvinceId")
    @ApiOperation(value="根据省份Id查找学校（app）",notes="返回值(学校对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "省份id", required = true ,dataType = "Integer",paramType = "query"),
           })
    public String getSchoolByProvinceId(Integer id){
        return JsonObject.toJson(shopClassification.getSchoolByProvice(id));
    }

    @ResponseBody
    @PostMapping("/getShopBySchoolId")
    @ApiOperation(value="根据学校id获取商铺（app）",notes="返回值(商铺对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "学校id", required = true ,dataType = "Integer",paramType = "query"),
    })
    public String getShopBySchoolId(Integer id){
        return JsonObject.toJson(shopClassification.selectShopBySchoolId(id));
    }

    @ResponseBody
    @PostMapping("/getSchoolByName")
    @ApiOperation(value="根据学校名模糊查找学校（app）",notes="返回值(学校对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "学校名称", required = true ,dataType = "String",paramType = "query"),
    })
    public String getShopBySchoolName(String name){
        return JsonObject.toJson(shopClassification.selectSchoolByName(name));
    }

    @ResponseBody
    @GetMapping("/getAllCategory")
    public String getProviceAndSchool() {
        List<Province> province = shopClassification.getAllProvice();
        List<ShopType> shopTypes = new LinkedList<>();
        for(int i=0;i<province.size();i++){
            ShopType shopType = new ShopType();
            int id = province.get(i).getId();
            shopType.setId(id);
            List<School> school = shopClassification.getSchoolByProvice(id);
            shopType.setName(province.get(i).getName());
            shopType.setSchool(school);
            shopTypes.add(shopType);
        }
        return JsonObject.toJson(shopTypes);
    }
//---------------
    @ResponseBody
    @PostMapping("/getAllLimit")
    @ApiOperation(value="查询所有并分页",notes="返回值(学校对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每页显示条数", required = true ,dataType = "Integer",paramType = "query"),
    })
    public String getAllLimit(int page,int num){
        return JsonObject.toJson(shopService.getAllLimit((page-1)*num,num));
    }



    @ResponseBody
    @PostMapping("/getAll")
    @ApiOperation(value="查询所有",notes="返回值(学校对象)数组或null")
    public String getAll(){
        return JsonObject.toJson(shopService.getAll());
    }

    @ResponseBody
    @PostMapping("/selectShopByQuery")
    @ApiOperation(value="根据条件查询所有",notes="返回值(学校对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商铺名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true ,dataType = "Integer",paramType = "query"),
    })
    public String selectShopByQuery(String name,int status){
        return JsonObject.toJson(shopService.selectShopByQuery(name,status));
    }

    @ResponseBody
    @PostMapping("/selectShopByQueryLimit")
    @ApiOperation(value="根据条件查询所有并分页",notes="返回值(学校对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "商铺名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "每页显示条数", required = true ,dataType = "Integer",paramType = "query"),
    })
    public String selectShopByQueryLimit(@RequestParam(value = "name")String name,@RequestParam(value = "status")int status,@RequestParam(value = "page")int page,@RequestParam(value = "num")int num){
        return JsonObject.toJson(shopService.selectShopByQueryLimit(name,status,(page-1)*num,num));
    }

    //update 2018-8-23
    @ResponseBody
    @PostMapping("/updateStatus")
    public String updateStatus(HttpSession httpSession,@RequestParam(value = "shopId",required = false) Integer shopId,@RequestParam(value = "status") Integer status){
        Integer sid = GetShop.get(shopId,httpSession);
        Shop shop = new Shop();
        shop.setId(sid);
        shop.setStatus(status);
        return shopService.updateByPrimaryKeySelective(shop)+"";
    }

}
