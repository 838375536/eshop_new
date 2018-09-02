package com.item.eshop.service;

import java.util.List;
import com.item.eshop.model.Comments;
import com.item.eshop.model.Recomments;
import org.apache.ibatis.annotations.Param;

public interface CommentsService {

    public List<Comments> selectByShopId(int shopId, int Page, int num);
    public List<Comments> selectAllByShopId(int shopId);

    public void insert(Comments commetns);
    void deleteByPrimaryKey(int id);
    List<Comments> selectByGoodId(int shopId, String goodName, int page, int n, int status);
    List<Recomments> selectByCommentsId(int commentId);
    List<Recomments> selectByCommentsIdLimit(int commentId, int page, int num);
    List<Recomments> selectByComment(int commentId, int page, int n);
    List<Recomments> selectAllByComment(int commentId);
    Comments selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Comments record);
    List<Comments> selectAllByGoodId(int shopId, String goodName, int status);
}
