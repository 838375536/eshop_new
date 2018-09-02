package com.item.eshop.controller;
import com.item.eshop.model.Notice;
import com.item.eshop.model.User;
import com.item.eshop.service.NoticeService;
import com.item.eshop.service.UserService;
import com.item.eshop.util.JsonObject;
import com.item.eshop.util.RepayPush;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.scheduler.Scheduler;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Api(value = "NoticeController", description = "（后台管理系统接口）营销规则增删改查等访问接口")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @Autowired
    UserService userService;

    // ======================  admin background management system interface ( user interceptor) =============================
    // category: 1:订单满减, 2:订单打折 3:充值满减  4:充值打折

    @PostMapping("/add")
    public int add(@RequestParam(value = "title")String title,
                   @RequestParam(value = "content")String content,
                      @RequestParam(value = "category")Integer category,
                      @RequestParam(value = "time",required = false)String time,
                    HttpSession httpSession) {
        Object res = httpSession.getAttribute("shopId");
        if(res==null){
            return 0;
        }
        Integer shopId = Integer.parseInt(res.toString());
        Notice notice = new Notice();
        notice.setCategory(category);
        notice.setContent(content);
        notice.setTitle(title);
        notice.setStatus(1);
        notice.setTimes(time);
        notice.setForeign_id(shopId);
        return noticeService.insertSelective(notice);
    }

    @PostMapping("/update")
    public int update(@RequestParam(value = "id")Integer id,
                   @RequestParam(value = "title",required = false)String title,
                   @RequestParam(value = "content",required = false)String content,
                   @RequestParam(value = "category",required = false)Integer category,
                   @RequestParam(value = "status",required = false)Integer status,
                   @RequestParam(value = "time",required = false)String time) {
        Notice notice = new Notice();
        notice.setContent(content);
        notice.setTitle(title);
        notice.setStatus(status);
        notice.setCategory(category);
        notice.setTimes(time);
        notice.setId(id);
        int res = noticeService.updateByPrimaryKeySelective(notice);
        if(id==0){
            List<User> users = userService.selectMore(1,99999);
            System.out.print("size1:::"+users.size());
            pushAlert(users,JsonObject.toJson(notice));
        }
        return res;
    }

    @PostMapping("/updateStatus")
    public int updateStatus(@RequestParam(value = "id",required = false)Integer id,
                   @RequestParam(value = "status",required = false)Integer status) {
        Notice notice = new Notice();
        notice.setStatus(status);
        notice.setId(id);
        List<User> users = userService.selectMore(1,99999);
        System.out.print("size:::"+users.size());
        int res = noticeService.updateByPrimaryKeySelective(notice);
        pushAlert(users,JsonObject.toJson(notice));
        return res;
    }

    @PostMapping("/delete")
    public int delete(@RequestParam(value = "id")Integer id) {
        return noticeService.deleteByPrimaryKey(id);
    }

    @PostMapping("/findByCategory")
    public String findByCategory(@RequestParam(value = "category")Integer category,@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        return JsonObject.toJson(noticeService.selectMoreByCategory(category,(page-1)*num,num));
    }

    @PostMapping("/findNotice")
    public String findByUser() {
        int id = 0;
        return JsonObject.toJson(noticeService.selectByPrimaryKey(id));
    }

    // ======================  admin background management system interface ( user interceptor) =============================

    @PostMapping("/findById")
    public String find(@RequestParam(value = "id")Integer id) {
        return JsonObject.toJson(noticeService.selectByPrimaryKey(id));
    }

    // add :  chan  2018/4/4
    @PostMapping("/findMore")
    public String findMore(@RequestParam(value = "page")Integer page,@RequestParam(value = "num")Integer num) {
        System.out.println("page:"+page+",num:"+num);
        return JsonObject.toJson(noticeService.selectMore((page-1)*num,num));
    }

    public static void pushAlert(List<User> users,String alert) {
        List<String> result = new ArrayList<>();
        int i=0;
        for (User user : users) {
            if(++i%20==0){
                RepayPush.init(result, alert);
                result = new ArrayList<>();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            result.add(user.getId() + "");
        }
        //RepayPush.init(result, alert);
    }

}
