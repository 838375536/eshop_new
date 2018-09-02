package com.item.eshop.controller;

import cn.felord.wepay.common.exception.PayException;
import com.alipay.api.domain.TradeRecord;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.Gson;
import com.item.eshop.model.*;
import com.item.eshop.service.*;
import com.item.eshop.util.Cache;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.TradeID;
import com.item.eshop.util.payment.AliPay;
import com.item.eshop.util.payment.WeiXinPay;
import com.item.eshop.util.websocket.TransJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

@Api(value = "RecommendController", description = "订单增删改查等访问接口")
@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    TradeService tradeService;
    @Autowired
    TradeGoodService tradeGoodService;
    @Autowired
    GoodService goodService;
    @Autowired
    DebtService debtService;
    @Autowired
    DebtRecordService debtRecordService;
    @Autowired
    UserCouponService userCouponService;
    @Autowired
    ShopService shopService;
    @Autowired
    CouponService couponService;
    @Autowired
    UserService userService;

    @Resource(name = "wxPayService")
    private WxPayService wxService;
//    @Resource(name="websocketSender")
//    WebsocketSender sender;

    // 2018-4-21  更新添加订单接口
    @ApiOperation(value = "添加订单", notes = "返回值（int):  1:成功，0：生成订单失败，2：购物车为空，3：商品不存在，4：生成订单失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "amount", value = "总价", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "coupon_id", value = "优惠券", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "address_id", value = "地址", required = true, dataType = "Integer", paramType = "query"),
    })
    @ResponseBody
    @Transactional
    @PostMapping("/add")
    public String add(@RequestParam(value = "shopId",required = false)Integer shopId,@RequestParam(value = "amount") double amount, @RequestParam(value = "coupon_id") Integer coupon_id, @RequestParam(value = "address_id") Integer address_id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        if (address_id == null) {
            return "5";
        }

        Trade t = new Trade();
        t.setAmount(new BigDecimal(amount));
        t.setFactAmount(new BigDecimal(amount));
        t.setAddressId(address_id);
        t.setShopId(shopId);
        if(coupon_id!=null){
            t.setCouponId(coupon_id.toString());
        }
        t.setUserId(user_id);
        return tradeService.insert(t);
    }


    // 2018-8-21  账号余额充值
    @ApiOperation(value = "充值金币", notes = "返回值（int):  1:成功，0：失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "amount", value = "总价", required = true, dataType = "Integer", paramType = "query"),
    })
    @ResponseBody
    @Transactional
    @PostMapping("/addMoney")
    public String addMoney(@RequestParam(value = "shopId",required = false)Integer shopId,@RequestParam(value = "amount") double amount, @RequestParam(value = "coupon_id",required = false) Integer coupon_id, @RequestParam(value = "address_id",required = false) Integer address_id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        if (address_id == null) {
            return "5";
        }

        Trade t = new Trade();
        t.setAmount(new BigDecimal(amount));
        t.setFactAmount(new BigDecimal(amount));
        if(shopId!=null)
            t.setShopId(shopId);
        else
            t.setShopId(9999999);
        t.setAddressId(address_id);
        if(coupon_id!=null&&coupon_id!=0){
            t.setCouponId(coupon_id+"");
        }
        t.setUserId(user_id);
        return tradeService.insertSelective(t)+"";
    }

    // 2018-8-15  更新充值账户接口
    @ApiOperation(value = "添加订单/充值", notes = "返回值（int):  1:成功，0：生成订单失败，2：购物车为空，3：商品不存在，4：生成订单失败")
    @ApiImplicitParams({@ApiImplicitParam(name = "amount", value = "总价", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "coupon_id", value = "优惠券", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "address_id", value = "地址", required = true, dataType = "Integer", paramType = "query"),
    })
    @ResponseBody
    @Transactional
    @PostMapping("/recharge")
    public int recharge(@RequestParam(value = "money") int money, @RequestParam(value = "pay_type") Integer pay_type, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        Trade t = new Trade();
        t.setAmount(new BigDecimal(money));
        t.setFactAmount(new BigDecimal(money));
        t.setShopId(0);
        t.setStatus(1);
        t.setStarttime(new Date());
        t.setContent("充值: "+money+" 元");
        List<Coupon> coupons = couponService.selectRule();
        for(Coupon c:coupons){
            if(money>=c.getTotal().intValue()){
                int result = money-c.getMinus().intValue();
                t.setCouponId(c.getId()+"");
                t.setFactAmount(new BigDecimal(result));
                break;
            }
        }
        t.setUserId(user_id);
        String uuid = TradeID.getWalletTradeId();
        t.setId(uuid);
        int res = tradeService.insertSelective(t);
        if(res==0){
            return 0;
        }
        String amount = t.getFactAmount().doubleValue() + "";
        String subject = "近距离-充值";
        String body = t.getContent();
        if (pay_type == 1) {
            AliPay.generate(uuid, amount, body, subject);
        } else if (pay_type == 2) {
            new WeiXinPay().generate(uuid, t.getFactAmount(), "211.97.6.117");
        }
        return 1;
    }



    @ResponseBody
    @PostMapping("/pay")
    public String pay(@RequestParam(value = "trade_no") String trade_no, @RequestParam(value = "pay_type") Integer pay_type, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        Trade trade;
        if ((trade = tradeService.selectByIdAndUserId(trade_no, user_id)) == null)
            return "订单已失效，请重新下单！";
        String amount = trade.getFactAmount().doubleValue() + "";
        String subject = "靖军的店-外卖";
        String body = trade.getContent();
        if (pay_type == 1) {
            return AliPay.generate(trade_no, amount, body, subject);
        } else if (pay_type == 2) {
            return new WeiXinPay().generate(trade_no, trade.getFactAmount(), "211.97.6.117");
        }
        return "无此支付方式！";
    }

//    @ResponseBody
//    @PostMapping("/updatePay")
//    public void updatePay(@RequestParam(value = "ali_trade_no") String ali_trade_no, @RequestParam(value = "trade_no") String trade_no, HttpSession httpSession) {
//        int user_id = (int) httpSession.getAttribute("user_id");
//        Trade trade;
//        System.out.println(trade_no + "," + user_id);
//        if ((trade = tradeService.selectByIdAndUserId(trade_no, user_id)) != null) {
//            String result = AliPay.check(trade_no, ali_trade_no);
//            System.out.println(result);
//            //trade.setStatus(2);
//
//            //tradeService.updateByPrimaryKeySelective(trade);
//        }
//    }

    //  2018/4/21  取消订单删除接口
//    @ApiOperation(value="删除某id值的订单",notes="返回值（int):  1:成功，0：失败")
//    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "订单id", required = true ,dataType = "Integer",paramType = "query"),
//            @ApiImplicitParam(name = "user_id", value = "用户id", required = true ,dataType = "Integer",paramType = "query")})
//    @ResponseBody
//    @PostMapping("/delete")
//    public int delete(@RequestParam(value = "id")String id, HttpSession httpSession) {
//        int user_id = (int) httpSession.getAttribute("user_id");
//        return tradeService.deleteByPrimaryKey(id);
//    }

    // add :  chan  2018/4/7
    @ApiOperation(value = "根据用户id值查找所有订单详情", notes = "返回值(Trade对象)数组或NULL值")
    @ApiImplicitParams({@ApiImplicitParam(name = "user_id", value = "用户id值", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "num", value = "展示数量", required = true, dataType = "Integer", paramType = "query")})
    @ResponseBody
    @PostMapping("/findByUser")
    public String findByUser(@RequestParam(value = "user_id") Integer userId, @RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        return JsonObject.toJson(tradeService.selectByUser(user_id, (page - 1) * num, num));
    }

    // add :  chan  2018-4-21
    @ApiOperation(value = "根据用户id值和订单状态查找所有订单详情", notes = "返回值(Trade对象)数组或NULL值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "订单状态", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "num", value = "展示数量", required = true, dataType = "Integer", paramType = "query")})
    @ResponseBody
    @PostMapping("/findByUserAndStatus")
    public String findByUserAndStatus(@RequestParam(value = "status") Integer status, @RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        return JsonObject.toJson(tradeService.selectByUserAndStatus(user_id, status, (page - 1) * num, num));
    }


    // add :  chan  2018-4-21
    @ApiOperation(value = "根据用户id值和多个订单状态值数组查找所有订单详情", notes = "返回值(Trade对象)数组或NULL值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "订单状态", required = true, dataType = "Integer", paramType = "query"),
//            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "Integer", paramType = "query"),
//            @ApiImplicitParam(name = "num", value = "展示数量", required = true, dataType = "Integer", paramType = "query")
    })
    @ResponseBody
    @PostMapping("/findByStatusSet")
    public String findByUserAndStatusArray(@RequestParam(value = "shopId",required = false) Integer shopId,@RequestParam(value = "status")String status, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        System.out.println("array:::status:"+status.substring(1,status.length()-1));
        List<Trade> trades =  tradeService.selectBySet(user_id,status.substring(1,status.length()-1));
        return JsonObject.toJson(trades);
    }


    @ResponseBody       //根据id值查询查询该用户订单
    @PostMapping("/findById")
    public String findById(@RequestParam(value = "id") String id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        return JsonObject.toJson(tradeService.selectByIdAndUserId(id, user_id));
    }

    @ResponseBody       //取消订单
    @PostMapping("/cancel")
    public int update(@RequestParam(value = "id") String id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        Trade trade = tradeService.selectByPrimaryKey(id);
        if (trade == null) {
            return 0;
        }
        if (trade.getUserId() != user_id)
            return 0;
        trade.setStatus(0);
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    @ResponseBody       //确认收货订单
    @PostMapping("/confirm")
    public int confirm(@RequestParam(value = "id") String id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        Trade trade = tradeService.selectByPrimaryKey(id);
        if (trade == null) {
            return 0;
        }
        if (trade.getUserId() != user_id)
            return 0;
        if(trade.getStatus()==3){
            trade.setStatus(4);
        }
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    @ResponseBody       //用户申请退货退款
    @PostMapping("/back")
    public int back(@RequestParam(value = "id") String id, HttpSession httpSession) {
        int user_id = (int) httpSession.getAttribute("user_id");
        Trade trade = tradeService.selectByPrimaryKey(id);
        int status = trade.getStatus();
        if (trade.getUserId() != user_id) {
            return 0;
        }
        if (status == 2) {
            trade.setStatus(5);
        } else if (status == 3 || status == 4) {
            trade.setStatus(6);
        }
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    //微信支付回调，更新数据库设置status=2
    @ResponseBody
    @PostMapping("/weixinpay")
    public String parseRefundNotifyResult(HttpServletRequest request, HttpServletResponse response) throws WxPayException { //@RequestBody String xmlData
        WxPayOrderNotifyResult params = new WeiXinPay().payNotify(request, response);
        int res = tradeService.wechatPay(params);
        return res+"";//this.wxService.parseRefundNotifyResult(xmlData);
    }


    @ResponseBody       //阿里支付回调接口，调用第三方接口
    @PostMapping("/alipay")
    @Transactional
    public boolean paid(HttpServletRequest request) {
        Map<String, String> params = AliPay.verify(request);
        int res = tradeService.aliPay(params);
        return res==0;
    }

    @ResponseBody       //退款，调用第三方接口
    @PostMapping("/refund")
    public int refund(@RequestParam(value = "id") String id, @RequestParam(value = "status") int toStatus) {
       int res = tradeService.refund(id,toStatus);
        return res;
    }

    @ResponseBody       //根据id值订单查询
    @PostMapping("/find")
    public String find(@RequestParam(value = "id") String id) {
        return JsonObject.toJson(tradeService.selectByPrimaryKey(id));
    }

    //管理员修改订单金额
    @ResponseBody
    @PostMapping("/updateAmount")
    public int update(@RequestParam(value = "id") String id, @RequestParam(value = "amount") double amount) {
        Trade trade = new Trade();
        trade.setId(id);
        trade.setFactAmount(new BigDecimal(amount));
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    //管理员修改地址
    @ResponseBody
    @PostMapping("/updateAddress")
    public int updateAddress(@RequestParam(value = "id") String id, @RequestParam(value = "address") String address) {
        Trade trade = new Trade();
        trade.setId(id);
        trade.setAddress(address);
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    //管理员修改地址和金额
    @ResponseBody
    @PostMapping("/updateTwo")
    public int updateTwo(@RequestParam(value = "id") String id, @RequestParam(value = "address") String address, @RequestParam(value = "amount") double amount) {
        Trade trade = new Trade();
        trade.setId(id);
        trade.setAddress(address);
        trade.setAmount(new BigDecimal(amount));
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    //管理员修改订单状态/ 发货/结算/确认退货，取消退货
    @ResponseBody
    @PostMapping("/updateStatus")
    public int update(@RequestParam(value = "id") String id, @RequestParam(value = "status") Integer status) {
        if(status==2){
            return 0;
        }
        Trade trade = new Trade();
        trade.setId(id);
        trade.setStatus(status);
        return tradeService.updateByPrimaryKeySelective(trade);
    }

    // add :  chan  2018/4/4
    @ResponseBody
    @PostMapping("/findMore")
    public String findMore(@RequestParam(value = "shopId") Integer shopId,@RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num) {
        return JsonObject.toJson(tradeService.selectMore(shopId,(page - 1) * num, num));
    }

    // add :  chan  2018/4/4
    @ResponseBody
    @PostMapping("/findByType")
    public String findByType(@RequestParam(value = "shopId") Integer shopId,@RequestParam(value = "status",required = false) Integer status, @RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num) {
        if(status==null||status==10){
            return JsonObject.toJson(tradeService.selectMore(shopId,(page - 1) * num, num));
        }
        List<Trade> trade = tradeService.selectByStatus(shopId,status, (page - 1) * num, num);
        return JsonObject.toJson(trade);
    }

    @ResponseBody
    @PostMapping("/findByTypes")
    public String findByTypes(@RequestParam(value = "shopId") Integer shopId,@RequestParam(value = "status",required = false) Integer status,@RequestParam(value = "status2",required = false) Integer status2, @RequestParam(value = "page") Integer page, @RequestParam(value = "num") Integer num) {
        String set =""+status+","+status2+")";
        List<Trade> trade = tradeService.selectBySets(shopId,set,(page-1)*num,num);
        return JsonObject.toJson(trade);
    }
}
