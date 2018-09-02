package com.item.eshop.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.item.eshop.model.Trade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TradeService {
    int deleteByPrimaryKey(String id);

    String insert(Trade record);

    int insertSelective(Trade record);

    Trade selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Trade record);

    int updateByPrimaryKey(Trade record);

    // add  : chan   2018/4/4
    List<Trade> selectByUser(Integer user_id, Integer page, Integer num);

    List<Trade> selectByStatus(Integer shopId,Integer status,Integer page,Integer num);

    List<Trade> selectMore(Integer shopId,Integer page,Integer num);

    // add : chan 2018-4-21
    List<Trade> selectByUserAndStatus(Integer user_id,Integer status,Integer page, Integer num);

    Trade selectByIdAndUserId(String id,Integer user_id);

    // add: chan 2018-5-7
    List<Trade> selectBySet(Integer user_id,String status);

    List<Trade> selectBySets(Integer shopId,String status,Integer page,Integer num);

    int updateByIdAndStatusOne(String tradeId);

    public boolean changPoint(int trade);

    //add 2018-8-28
    public int wechatPay(WxPayOrderNotifyResult params);

    public int aliPay( Map<String, String> params);

    public int refund( String id, int toStatus);
}
