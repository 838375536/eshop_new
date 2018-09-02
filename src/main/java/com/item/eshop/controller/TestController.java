package com.item.eshop.controller;

import com.item.eshop.util.websocket.TransJson;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Api(value = "testController", description = "测试访问接口")
@Controller
@RequestMapping("/test")
public class TestController {
//    @Resource(name="websocketSender")
//    WebsocketSender sender;
//
//    @ResponseBody
//    @PostMapping("/websocket")
//    public String getMsg(@RequestParam(value = "msg")String msg) {
//        sender.sendSingle("2",new TransJson(1,msg));
//        return "websocket";
//    }
}
