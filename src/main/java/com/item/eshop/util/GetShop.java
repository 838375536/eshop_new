package com.item.eshop.util;

import javax.servlet.http.HttpSession;

public class GetShop {
    public static Integer get(Integer sid, HttpSession httpSession){
        Integer shopId=-1;
        if(sid!=null)
            shopId = sid;
        else
            shopId = Integer.parseInt(httpSession.getAttribute("shopId").toString());
        if(shopId==null)
            return shopId;
        return shopId;
    }
}
