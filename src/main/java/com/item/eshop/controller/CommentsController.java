package com.item.eshop.controller;

import com.item.eshop.mapper.CommentsMapper;

import com.item.eshop.mapper.RecommentsMapper;
import com.item.eshop.model.Recomments;
import com.item.eshop.service.CommentsService;
import com.item.eshop.service.RecommentsService;
import com.item.eshop.util.GetShop;
import com.item.eshop.util.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.spring.web.json.Json;
import com.item.eshop.model.Comments;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Api(value = "comments", description = "（后台管理系统接口）论坛去评论增删查等访问接口")
@Controller
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    CommentsService commentsService;
    @Autowired
    RecommentsService recommentsService;


    @ApiOperation(value = "(App端) 根据商家ID分页查询商品评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "商家ID", required = true ,dataType = "Integer",paramType = "query"),@ApiImplicitParam(name = "page", value = "总页数", required = true ,dataType = "Integer",paramType = "query"),@ApiImplicitParam(name = "num", value = "每页显示条数", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @GetMapping("/getCommentsLimit")
    public String getCommentsLimit(HttpSession httpSession,@RequestParam(value = "shopId",required = false) Integer  shopId, @RequestParam(value = "page") int  page, @RequestParam(value = "num") int  num) {
        int sid = GetShop.get(shopId,httpSession);
        return JsonObject.toJson(commentsService.selectByShopId(sid,(page - 1) * num,num));
    }



    @ApiOperation(value = "(App端) 根据商家ID查询所有商品评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "商家ID", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @GetMapping("/getAllCommentsLimit")
    public String getAllCommentsLimit(HttpSession httpSession,@RequestParam(value = "shopId",required = false) Integer  shopId) {
        int sid = GetShop.get(shopId,httpSession);
        return JsonObject.toJson(commentsService.selectAllByShopId(sid));
    }



    @ApiOperation(value = "(App端) 插入商品评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户ID", required = true ,dataType = "String",paramType = "query"),@ApiImplicitParam(name = "shopId", value = "商家ID", required = true ,dataType = "Integer",paramType = "query")
            ,@ApiImplicitParam(name = "content", value = "评论内容", required = false ,dataType = "String",paramType = "query"),@ApiImplicitParam(name = "goodId", value = "商品ID", required = true ,dataType = "Integer",paramType = "query")})
    @GetMapping("/insertComments")
    public void insertComments(@RequestParam(value = "phone") String  phone,@RequestParam(value = "goodId") int  goodId,@RequestParam(value = "shopId") int  shopId,@RequestParam(value = "content") String   contents) {
        Comments comments=new Comments();
        Date date=new Date();
        comments.setContent(contents);
        comments.setGoodId(goodId);
        comments.setPhone(phone);
        comments.setSendtime(date);
        comments.setShoId(shopId);
        comments.setstatus(0);
       commentsService.insert(comments);
    }




    @ApiOperation(value = "商家回复用户评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "shopId", value = "商家ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "commentsId", value = "评论表ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "content", value = "回复内容", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "回复用户", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "image", value = "回复上传图片", required = true ,dataType = "String",paramType = "query")
            })
    @ResponseBody
    @GetMapping("/insertReComments")
    public void insertReComments(@RequestParam(value = "shopId") int  shopId,@RequestParam(value = "commentsId") int  commentsId,@RequestParam(value = "image") String  image,@RequestParam(value = "phone") String  phone,@RequestParam(value = "content") String  content) {
        Recomments recomments=new Recomments();
        recomments.setCommentsId(commentsId);
        recomments.setContents(content);
        recomments.setPhone(phone);
        if(image!=null&&image!="")
        recomments.setImage(image);
        recomments.setShopId(shopId);
        Date date=new Date();
        recomments.setSendtime(date);
        Comments comment=new Comments();
        comment.setstatus(1);
        comment.setShoId(shopId);
        comment.setId(commentsId);
        commentsService.updateByPrimaryKeySelective(comment);
        recommentsService.insert(recomments);
    }


    @ApiOperation(value = "撤回回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "recommentsId", value = "消息ID", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @GetMapping("/deleteReComments")
    public void deleteReComments(@RequestParam(value = "recommentsId") int  recommentsId) {
        recommentsService.deleteByPrimaryKey(recommentsId);

    }



    @ApiOperation(value = "删除评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "commentsId", value = "评论ID", required = true ,dataType = "Integer",paramType = "query")})
    @ResponseBody
    @GetMapping("/deleteComments")
    public void deleteComments(@RequestParam(value = "commentsId") int  commentsId) {
        commentsService.deleteByPrimaryKey(commentsId);

    }




    @ApiOperation(value = "返回条件查询总数")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodId", value = "商品ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "goodName", value = "商品名号", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true ,dataType = "Integer",paramType = "query"),

    })
    @ResponseBody
    @GetMapping("/checkAllByGoodId")
    public String  checkAllByGoodId(@RequestParam(value = "shopId") int  shopId,@RequestParam(value = "status") int  status,@RequestParam(value = "goodName") String  goodName) {
        return JsonObject.toJson(commentsService.selectAllByGoodId(shopId,goodName,status));
    }



    @ApiOperation(value = "根据条件搜索相应评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "goodId", value = "商品ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "goodName", value = "商品名号", required = true ,dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),@ApiImplicitParam(name = "n", value = "每页条数", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @GetMapping("/checkByGoodId")

    public String  checkByGoodId(@RequestParam(value = "shopId") int  shopId,@RequestParam(value = "status") int  status,@RequestParam(value = "goodName") String  goodName,@RequestParam(value = "page") int  page,@RequestParam(value = "n") int  n) {
        return JsonObject.toJson(commentsService.selectByGoodId(shopId,goodName,(page-1)*n,n,status));
    }



    @ApiOperation(value = "根据评论ID搜索相应回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "commentsID", value = "评论ID", required = true ,dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "page", value = "页数", required = true ,dataType = "Integer",paramType = "query"),@ApiImplicitParam(name = "n", value = "每页条数", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @GetMapping("/checkByCommentsId")
    public String  checkByCommentsId(@RequestParam(value = "commentsId") int  commentsId,@RequestParam(value = "page") int  page,@RequestParam(value = "n") int  n) {
        return JsonObject.toJson(commentsService.selectByComment(commentsId,page,n));
    }





    @ApiOperation(value = "根据评论ID搜索相应回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "commentsID", value = "评论ID", required = true ,dataType = "Integer",paramType = "query")
    })
    @ResponseBody
    @GetMapping("/checkAllByCommentsId")
    public String  checkAllByCommentsId(@RequestParam(value = "commentsId")int commentsId) {
        return JsonObject.toJson(commentsService.selectAllByComment(commentsId));
    }

}


