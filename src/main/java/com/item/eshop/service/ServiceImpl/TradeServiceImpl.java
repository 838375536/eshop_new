package com.item.eshop.service.ServiceImpl;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.item.eshop.controller.CouponController;
import com.item.eshop.controller.UserController;
import com.item.eshop.mapper.*;
import com.item.eshop.model.*;
import com.item.eshop.service.DebtRecordService;
import com.item.eshop.service.DebtService;
import com.item.eshop.service.TradeService;
import com.item.eshop.service.UserService;
import com.item.eshop.util.Cache;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.TradeID;
import com.item.eshop.util.payment.AliPay;
import com.item.eshop.util.payment.WeiXinPay;
import com.item.eshop.util.websocket.TransJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service(value = "tradeService")
public class TradeServiceImpl implements TradeService {

    @Autowired
    TradeMapper tradeMapper;

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    ShopcarGoodMapper shopcarGoodMapper;

    @Autowired
    CouponMapper couponMapper;

    @Autowired
    UserCouponMapper userCouponMapper;


    @Autowired
    AddressMapper addressMapper;

    @Autowired
    TradeGoodMapper tradeGoodMapper;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    UserService userService;

    @Autowired
    DebtService debtService;

    @Autowired
    DebtRecordService debtRecordService;

//    @Resource(name="websocketSender")
//    WebsocketSender sender;


    @Override
    public int deleteByPrimaryKey(String id) {
        return tradeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public String insert(Trade record) {
        //获取用户地址
        Address address = addressMapper.selectByPrimaryKey(record.getAddressId());
        if(address==null)
            return "5";
        String addr = address.getProvince()+""+address.getCity()+""+address.getDistrict()+""+address.getStreet()+"@"+address.getName()+"@"+address.getPhone();
        List<ShopcarGood> shopcarGood = shopcarGoodMapper.selectByUser(record.getUserId());
        int shopId = -1;
        if(shopcarGood==null)
            return "2";
        String ids = "";
        for(ShopcarGood sg:shopcarGood){
            ids+= sg.getGoodId() + ",";
        }
        ids+= "-1";
        List<Good> goods = goodMapper.selectBySet(ids);
        if(goods==null){
            return "3";
        }else{
            shopId = goods.get(0).getShopId();
        }

        String uuid = TradeID.getID();
        TradeGood tradeGood;
        //计算金额
        double price = 0;
        String content = "";
        String image = null;

        //商品风格
        String good_type = "";          // 2018-5-15
        String other = "";              // 2018-5-15
        String sub_content = ""; //"("+ good_type.split("@")[Integer.parseInt(other)]+")";    // 2018-5-15

        Integer temp_good = 0;

        Map goodCount = new HashMap<>();
        List<Object> goodContent = new ArrayList<>();
        for(ShopcarGood sg:shopcarGood){
            other = sg.getOther();      // 2018-5-15
            for(Good good: goods){
                   if(other!=null) {             // 2018-5-15
                       good_type = good.getContent();   // 2018-5-15
                       sub_content = "("+ good_type.split("@")[Integer.parseInt(other)]+")"; // 2018-5-15
                   }
                if(sg.getGoodId()==good.getId()){
                    price += good.getPrice().doubleValue()*sg.getCount();
                    content += good.getName()+sub_content+":"+good.getPrice()+"元/"+sg.getCount()+"个 ; ";        // 2018-5-15
                    if(image==null){
                        image = good.getImage();
                    }
                    goodCount.put("good",good);
                    goodCount.put("count",sg.getCount());
                    goodContent.add(goodCount);
                    goodCount = new HashMap<>();
                }
                sub_content = ""; // 2018-5-15
            }
            tradeGood = new TradeGood();
            tradeGood.setCount(sg.getCount());
            tradeGood.setGoodId(sg.getGoodId());
            tradeGood.setTradeId(uuid);
            tradeGood.setOther(other);
            temp_good = sg.getGoodId();
            if(tradeGoodMapper.insertSelective(tradeGood)==0){
                return "4";
            }
        }
        if(content!=null&&content.length()>3) {
            content = content.substring(0, content.length() - 2);
        }
        if(shopcarGoodMapper.deleteByUser(record.getUserId())==0){
            return "4";
        }

        Trade trade = record;
        double fact_price = price;
        int CouponId = 0;
        String rc = record.getCouponId();
        if(rc!=null){
            CouponId = Integer.parseInt(rc);
        }
        if(CouponId!=0) {
            UserCoupon userCoupon = userCouponMapper.selectByUserAndCoupon(record.getUserId(),CouponId);
            if (userCoupon != null&&userCoupon.getDeadline().getTime()>Calendar.getInstance().getTimeInMillis()&&userCoupon.getValid()==1) {
                Coupon uc = couponMapper.selectByPrimaryKey(CouponId
                );
                if(uc!=null&&price>=uc.getTotal().doubleValue()){
                    int result = userCouponMapper.useCoupon(CouponId,record.getUserId());
                    if(result==1)
                    {
                        fact_price = price - uc.getMinus().doubleValue();
                        trade.setCouponId("满 " + uc.getTotal().doubleValue() + " 减 " + uc.getMinus().doubleValue());
                    }
                }
            }
        }
        trade.setId(uuid);
        trade.setStarttime(new Date());
        trade.setAmount(new BigDecimal(price));
        trade.setFactAmount(new BigDecimal(fact_price));
        trade.setAddress(addr);
        trade.setContent(content);
        trade.setImage(image);
        trade.setShopId(shopId);
        trade.setTradeGood(JsonObject.toJson(goodContent));
        if(tradeMapper.insertSelective(trade)==0){
            return "0";
        }
        //Integer shopIds = goodMapper.selectShopByGood(temp_good);
        //Shop seller = shopMapper.selectByPrimaryKey(shopId);
//        TransJson msg = new TransJson();
//        msg.setCode(1);//add
//        msg.setMsg("你有新订单了，请及时处理");
//        sender.sendSingle(seller.getUser_id().toString(),msg);
        return uuid;
    }

    @Override
    public int insertSelective(Trade record) {
        String uuid = TradeID.getWalletTradeId();
        Trade trade = record;
        trade.setId(uuid);
        return tradeMapper.insertSelective(trade);
    }

    @Override
    public Trade selectByPrimaryKey(String id) {
        return tradeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Trade record) {
        return tradeMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Trade record) {
        return tradeMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Trade> selectByUser(Integer user_id, Integer page, Integer num) {
        return tradeMapper.selectByUser(user_id,page,num);
    }

    @Override
    public List<Trade> selectByStatus(Integer shopId,Integer status, Integer page, Integer num) {
        return tradeMapper.selectByStatus(shopId,status,page,num);
    }

    @Override
    public List<Trade> selectMore(Integer shopId,Integer page, Integer num) {
        return tradeMapper.selectMore(shopId,page,num);
    }

    @Override
    public List<Trade> selectByUserAndStatus(Integer user_id, Integer status, Integer page, Integer num) {
        List<Trade> trades = tradeMapper.selectByUserAndStatus(user_id, status, page, num);
        return trades;
    }

    @Override
    public Trade selectByIdAndUserId(String id, Integer user_id) {
        Trade trade = tradeMapper.selectByIdAndUserId(id,user_id);
        return trade;
    }

    @Override
    public List<Trade> selectBySet(Integer user_id,String status) {
        return tradeMapper.selectBySet(user_id,status);
    }

    @Override
    public List<Trade> selectBySets(Integer shopId,String status, Integer page, Integer num) {
        return tradeMapper.selectBySets(shopId,status, page, num);
    }

    @Override
    public int updateByIdAndStatusOne(String tradeId) {
        return tradeMapper.updateByIdAndStatusOne(tradeId);
    }


    //minus the point when user pay the trade
    @Override
    public boolean changPoint(int  tradeId) {
        if(tradeGoodMapper.selectByPrimaryKey(tradeId)==null){
            //calculate the total of the point
            List<Good> goodList=tradeMapper.getTradeGoodList(tradeId);
            int total=0;
            for(Good g:goodList ){
                int count=g.getCounts();
                int point=Integer.valueOf(g.getOther());
                total =total+count*point;
            }
            //check  the point of user
            User user=tradeMapper.getTradeUserPoint(tradeId);
            int userPoint=Integer.valueOf(user.getOther());
            user.setOther(String.valueOf(userPoint+total));
        }
        return false;
    }

    @Transactional
    @Override
    public int wechatPay(WxPayOrderNotifyResult params) {
        if (params.getResultCode().equals("SUCCESS")){
            String trade_no = params.getOutTradeNo();
            String wx_trade_no = params.getTransactionId();
            BigDecimal money = new BigDecimal(params.getCashFee()/100);
            System.out.println(">>>>>>>>>>>>>check debt repay:"+trade_no+",length:"+trade_no.length());
            if(trade_no.length()>17){
                Trade trade = tradeMapper.selectByPrimaryKey(trade_no);
                trade.setStatus(4);
                User user = userService.selectUser(trade.getUserId());
                money = user.getMoney().add(money);
                user.setMoney(money);
                userService.updateUser(user);
                return tradeMapper.updateByPrimaryKeySelective(trade);
            } else
            if (trade_no.length() > 15) {
                Trade trade = selectByPrimaryKey(trade_no);
                if(trade==null)
                    return 0;
                trade.setId(trade_no);
                trade.setPay_type(2);
                trade.setPay_trade_no(wx_trade_no);
                if(trade.getStatus()==1) {
                    trade.setStatus(2);
                }
                trade.setEndtime(Calendar.getInstance().getTime());
                updateByPrimaryKeySelective(trade);
            } else {
                String uid = Cache.getRedis(trade_no);
                System.out.println(">>>>>>>>>>>>>check debt repay:steplen<=13,"+uid);
                if(uid==null)
                    return 0;
                int user_id = Integer.parseInt(uid);
                Debt debt = debtService.selectByPrimaryKey(user_id);
                DebtRecord debtRecord = new DebtRecord();
                debtRecord.setId(trade_no);
                debtRecord.setOut_trade_no(wx_trade_no);
                debtRecord.setCost(debt.getCost());
                debtRecord.setUsetime(Calendar.getInstance().getTime());
                debtRecord.setDebt_id(user_id);
                debtRecord.setStatus(2);

                debt.setId(user_id);
                debt.setCost(new BigDecimal(0.00));
                debt.setValid(2);
                debtService.updateByPrimaryKeySelective(debt);
                debtRecordService.insertSelective(debtRecord);
            }
        }
        return 0;
    }

    @Transactional
    @Override
    public int aliPay(Map<String, String> params) {
        System.out.println(params.get(""));
        String ali_trade_no = params.get("trade_no");
        String trade_no = params.get("out_trade_no");
        String amount = params.get("total_amount");
        BigDecimal money = new BigDecimal(amount);
        if(trade_no.length()>17){
            Trade trade = selectByPrimaryKey(trade_no);
            trade.setStatus(4);
            User user = userService.selectUser(trade.getUserId());
            money = user.getMoney().add(money);
            user.setMoney(money);
            userService.updateUser(user);
            return updateByPrimaryKeySelective(trade);
        } else
        if (trade_no.length() > 13) {
            Trade trade = selectByPrimaryKey(trade_no);
            if(trade==null)
                return 0;
            trade.setId(trade_no);
            trade.setPay_type(1);
            trade.setPay_trade_no(ali_trade_no);
            if(trade.getStatus()==1) {
                trade.setStatus(2);
            }
            trade.setEndtime(Calendar.getInstance().getTime());
            updateByPrimaryKeySelective(trade);
            //2018-8-11
            TransJson msg = new TransJson();
            msg.setCode(2);//paid
            msg.setMsg("你有新订单了，请及时处理");
//            Trade t = tradeService.selectByPrimaryKey(trade_no);
//            Integer shopId = t.getShopId();
//            Shop seller = shopService.selectByPrimaryKey(shopId);
//            sender.sendSingle(seller.getUser_id().toString(),msg);

        } else {

            String uid = Cache.getRedis(trade_no);
            if(uid==null)
                return 0;
            int user_id = Integer.parseInt(Cache.getRedis(trade_no));
            Debt debt = debtService.selectByPrimaryKey(user_id);
            DebtRecord debtRecord = new DebtRecord();
            debtRecord.setId(trade_no);
            debtRecord.setOut_trade_no(ali_trade_no);
            debtRecord.setCost(debt.getCost());
            debtRecord.setUsetime(Calendar.getInstance().getTime());
            debtRecord.setDebt_id(user_id);
            debtRecord.setStatus(2);
            debt.setId(user_id);
            debt.setCost(new BigDecimal(0.00));
            debt.setValid(2);
            debtService.updateByPrimaryKeySelective(debt);
            debtRecordService.insertSelective(debtRecord);
        }
        return 0;
    }

    @Transactional
    @Override
    public int refund(String id, int toStatus) {
        Trade trade = selectByPrimaryKey(id);
        int status = trade.getStatus();
        boolean result = false;
        if (status == 1 || status == 0 || status == 7) {
            return 0;
        }
        if (toStatus == 8) {
            trade.setStatus(8);
            updateByPrimaryKeySelective(trade);
            return 1;
        }
        BigDecimal amount = trade.getFactAmount();
        String out_trade_no = trade.getPay_trade_no();
        if (trade.getPay_type() == 1) {
            result = AliPay.refund(id, out_trade_no, amount);
        } else if (trade.getPay_type() == 2) {
            result = new WeiXinPay().refund(trade);
        } else if (trade.getPay_type() == 3) {
            Debt debt = new Debt();
            debt.setId(trade.getUserId());
            debt.setCost(new BigDecimal(0.00));
            DebtRecord debtRecord = new DebtRecord();
            debtRecord.setId(TradeID.getDebtRecordId());
            debtRecord.setDebt_id(trade.getUserId());
            debtRecord.setTrade_no(trade.getId());
            debtRecord.setStatus(3);
            debtRecord.setCost(trade.getFactAmount());
            debtRecord.setUsetime(Calendar.getInstance().getTime());
            int result1 = debtRecordService.insertSelective(debtRecord);
            int result2 = debtService.updateByPrimaryKeySelective(debt);
            if(result1==1&&result2==1){
                result = true;
            }
        }
        if (result) {
            trade.setStatus(7);
            return updateByPrimaryKeySelective(trade);
        }
        return 0;
    }
}
