package com.item.eshop.controller;


import com.item.eshop.config.Constant;
import com.item.eshop.mapper.ReportMapper;
import com.item.eshop.model.Status;
import com.item.eshop.service.UserService;
import com.item.eshop.util.Cache;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.RepayPush;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Api(value = "/announcement", description = "通知访问接口（暂时不可用）")
@Controller
public class AnnouncementController {

    @Autowired
    Constant constant;
    @Autowired
    UserService userService;


    @GetMapping("/fakeTip")  //对已登录的用户发送下单消息
    public String index(int time,String content){
        int interval = time;
        if(interval==-1){
            interval = (int)(Math.random()*5);
            Set<String> ids = Cache.showUserIds("userIds");
            List<String> result = new ArrayList<>();
            for (String id : ids) {
                result.add(id);
            }
            RepayPush.init(result, content);
        }
        return "";
    }



}
