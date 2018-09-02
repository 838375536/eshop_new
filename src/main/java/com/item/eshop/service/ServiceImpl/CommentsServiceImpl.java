package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.CommentsMapper;
import com.item.eshop.mapper.RecommentsMapper;
import com.item.eshop.model.Comments;
import com.item.eshop.model.Recomments;
import com.item.eshop.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("/commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    RecommentsMapper RecommentsMapper;

    public List<Comments> selectByShopId(int shopid, int page, int num) {
        return commentsMapper.selectByShopId(shopid, page, num);
    }

    public List<Comments> selectAllByShopId(int shopId) {
        return commentsMapper.selectAllByShopId(shopId);
    }

    public void insert(Comments comments) {
        commentsMapper.insert(comments);
    }

    public void deleteByPrimaryKey(int id) {
        commentsMapper.deleteByPrimaryKey(id);
    }

    public List<Comments> selectByGoodId(int shopId, String goodName, int page, int n,int status) {
        return commentsMapper.selectByGoodId(shopId, goodName, page, n , status);
    }

    public List<Recomments> selectByCommentsId(int commentId) {
        return RecommentsMapper.selectByCommentsId(commentId);
    }

    public List<Recomments> selectByCommentsIdLimit(int commentId, int page, int num) {
        return RecommentsMapper.selectByCommentsIdLimit(commentId, page, num);
    }

    public List<Recomments> selectByComment(int commentId, int page, int n) {
        return commentsMapper.selectByComment(commentId, page, n);
    }
    public List<Recomments> selectAllByComment(int commentId){return commentsMapper.selectAllByComment(commentId);}
    public Comments selectByPrimaryKey(Integer id){return commentsMapper.selectByPrimaryKey(id);}
    public int updateByPrimaryKeySelective(Comments record){return commentsMapper.updateByPrimaryKeySelective(record);}
    public List<Comments> selectAllByGoodId(int shopId ,String goodName,int status ){
        return commentsMapper.selectAllByGoodId(shopId,goodName,status);
    }

}
