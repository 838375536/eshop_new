package com.item.eshop.controller;

import com.item.eshop.config.Constant;
import com.item.eshop.model.Good;
import com.item.eshop.model.User;
import com.item.eshop.service.GoodService;
import com.item.eshop.service.ShopcarGoodService;
import com.item.eshop.service.TradeGoodService;
import com.item.eshop.service.TradeService;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.UploadImage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Api(value = "GoodController", description = "商品增删改查等访问接口")
@Controller
@RequestMapping("/good")
public class GoodController {

    @Autowired
    Constant constant;

    @Autowired
    GoodService goodService;

    @Autowired
    ShopcarGoodService shopcarGoodService;

    @Autowired
    TradeService tradeService;

    @Autowired
    TradeGoodService tradeGoodService;

    @ApiOperation(value="（app端/后台端）根据id值查找商品",notes="返回值(商品对象)或null")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @PostMapping("/find")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(goodService.selectByPrimaryKey(id));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="（app端/后台端）按序根据商家id获取一页的所有商品",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "商鋪id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @PostMapping("/findMore")
    public String findMore(@RequestParam(value = "shopId")Integer shopId,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(goodService.selectMore(shopId,(page-1)*num,num));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="（app端/后台端）根据商家id和商品状态 获取一页的所有商品",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "商铺id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "商品状态", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/findByStatus")
    public String findByStatus(@RequestParam(value = "shopId")Integer shopId,@RequestParam(value = "status")Integer status,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        if(status==10){
            return JsonObject.toJson(goodService.selectMore(shopId,(page-1)*num,num));
        }
        return JsonObject.toJson(goodService.selectByStatus(shopId,status,(page-1)*num,num));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="（app端/后台端）根据商家id和商品类型 获取一页的所有商品",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId", value = "商铺id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "category", value = "商品展示类型", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query")
            })
    @ResponseBody
    @PostMapping("/findByCategory")
    public String findByCategory(@RequestParam(value="shopId")Integer shopId,@RequestParam(value = "category")Integer category,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(goodService.selectByCategory(shopId,category,(page-1)*num,num));
    }

    // add :  chan  2018/4/19
    @ApiOperation(value="（app端/后台端）按likes点赞数排序根据页数查找",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "category", value = "商品展示类型", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @PostMapping("/findBySort")
    public String findBySort(@RequestParam(value = "shopId")Integer shopId,@RequestParam(value = "category")Integer category,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(goodService.selectBySort(shopId,category,(page-1)*num,num));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="（app端/后台端） 按序根据商家id获取一页的推荐商品",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "other", value = "商品展示类型", required = true ,dataType = "String",paramType = "query")})
    @ResponseBody
    @PostMapping("/findByRecommend")
    public String findByRecommend(HttpSession httpSession,@RequestParam(value = "shopId",required = false)Integer shopId,@RequestParam(value = "other")String other,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        String sid = httpSession.getAttribute("shopId").toString();
        int sessionShopId = -1;
        if(sid!=null){
            sessionShopId = Integer.parseInt(sid);
        }
        return JsonObject.toJson(goodService.selectByOther(sessionShopId,other,(page-1)*num,num));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="（app端）点赞商品",notes="返回值Int：1：成功，0：失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", required = true ,dataType = "Integer",paramType = "query")
            })
    @ResponseBody
    @PostMapping("/like")
    public int updateLike(@RequestParam(value = "id")Integer id) {
        Good  good = new Good();
        good.setId(id);
        Good g = goodService.selectByPrimaryKey(id);
        g.setLikes(g.getLikes()+1);
        return goodService.updateByPrimaryKeySelective(g);
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    // add :  chan  2018/4/4
    @ApiOperation(value="（后台端）按序根据页数查找相似名称商品",notes="返回值(商品对象)数组或null")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "num", value = "商品展示数量", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "name", value = "商品展示类型", required = true ,dataType = "String",paramType = "query")})
    @ResponseBody
    @PostMapping("/findByName")
    public String findByName(@RequestParam(value = "shopId")Integer shopId,@RequestParam(value = "name")String name,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(goodService.selectByName(shopId,"%"+name+"%",(page-1)*num,num));
    }

    // add :  chan  2018/4/4
    @ApiOperation(value="更新推荐商品",notes="返回值Int：1：成功，0：失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "页数", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "other", value = "商品展示类型", required = true ,dataType = "String",paramType = "query")})
    @ResponseBody
    @PostMapping("/updateByRecommend")
    public int updateByRecommend(@RequestParam(value = "id")Integer id,@RequestParam(value = "other")String other) {

        Good  good = new Good();
        good.setId(id);
        good.setOther(other);
        return goodService.updateByPrimaryKeySelective(good);
    }

    @ApiOperation(value="（后台端）添加商品",notes="返回值Int：1：成功，0：失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "商品id", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/add")
    public int add(HttpSession httpSession,@RequestParam(value = "image",required = false) MultipartFile image, @RequestParam(value = "name") String name,
                   @RequestParam(value = "introduce",required = false)  String introduce, @RequestParam(value = "content",required = false) String content,
                   @RequestParam(value = "price") double price, @RequestParam(value = "count") Integer count,
                   @RequestParam(value = "category") Integer category,@RequestParam(value = "status") Integer status,@RequestParam(value = "shopId",required = false) Integer shopId) {
        String sid = httpSession.getAttribute("shopId").toString();
        int sessionShopId = -1;
        if(sid!=null){
            sessionShopId = Integer.parseInt(sid);
        }
        if(image==null) {
            return 0;
        }
        Good good = new Good();
        good.setCategory(category);
        good.setPrice(BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP));
        good.setCounts(count);
        good.setContent(content);
        good.setIntroduce(introduce);
        good.setName(name);
        if(sessionShopId!=-1){
            good.setShopId(sessionShopId);
        }else {
            good.setShopId(shopId);
        }
        String url = new UploadImage().uploadImage(image,0,constant.getPath(),constant.getUrl());
        good.setImage(url);
        return goodService.insertSelective(good);
    }

    //    @ResponseBody
//    @PostMapping("/delete")
//    public int delete(@RequestParam(value = "id")Integer id) {
//        return goodService.deleteByPrimaryKey(id);
//    }

    @ApiOperation(value="（后台端）更新商品",notes="返回值Int：1：成功，0：失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "image", value = "商品图片", required = true ,dataType = "File",paramType = "query"),
            @ApiImplicitParam(name = "image", value = "商品名称", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "introduce", value = "商品介绍", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "price", value = "商品价格", required = true ,dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "count", value = "商品库存", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "category", value = "商品类型", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "商品状态，0：商品回收站；1：商品上架；2：商品下架", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "shopId", value = "商铺id", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/update")
    public int update(@RequestParam(value = "id")Integer id, @RequestParam(value = "image",required = false) MultipartFile image, @RequestParam(value = "name") String name,
                      @RequestParam(value = "introduce",required = false)  String introduce,
                      @RequestParam(value = "price") double price, @RequestParam(value = "count") Integer count,
                      @RequestParam(value = "category") Integer category, @RequestParam(value = "status") Integer status, @RequestParam(value = "shopId",required = false) Integer shopId, HttpSession session) {
        Good good = new Good();
        good.setId(id);
        good.setCategory(category);
        good.setPrice(BigDecimal.valueOf(price).setScale(2,BigDecimal.ROUND_HALF_UP));
        good.setCounts(count);
        good.setIntroduce(introduce);
        good.setName(name);
        //good.setShopId(shopId);
        if(image!=null) {
            String url = new UploadImage().uploadImage(image, 0, constant.getPath(), constant.getUrl());
            good.setImage(url);
        }
        return goodService.updateByPrimaryKeySelective(good);
    }

    @ApiOperation(value="（后台端）更新商品",notes="返回值Int：1：成功，0：失败")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品id", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "商品状态，0：商品回收站；1：商品上架；2：商品下架", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @PostMapping("/updateStatus")
    public int update(@RequestParam(value = "id")Integer id,@RequestParam(value = "status")Integer status) {
        Good good = new Good();
        good.setId(id);
        good.setStatus(status);
        int result = goodService.updateByPrimaryKeySelective(good);
        if(result==1&&(status==0||status==2)){
            shopcarGoodService.deleteByGood(id);
            List<String> tradeIds = tradeGoodService.selectByGood(id);
            for(String tradeId:tradeIds){
                tradeService.updateByIdAndStatusOne(tradeId);
            }
        }
        return 0;
    }



}
