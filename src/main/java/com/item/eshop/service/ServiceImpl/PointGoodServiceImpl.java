package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.PointGoodMapper;
import com.item.eshop.mapper.UserMapper;
import com.item.eshop.model.PointGood;
import com.item.eshop.model.User;
import com.item.eshop.service.PointGoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointGoodServiceImpl implements PointGoodService {
    @Autowired
    PointGoodMapper pointGoodMapper;
    @Autowired
    UserMapper userMapper;
    public List<PointGood> selectByQuery(String name,int status){
        return pointGoodMapper.selectByQuery(name,status);
    }

    @Override
    public List<PointGood> selectByQueryLimit(String name, int status, int page, int num) {
        return pointGoodMapper.selectByQueryLimit(name,status,page,num);
    }

    @Override
    public PointGood getById(int id) {
        return pointGoodMapper.selectByPrimaryKey(id);
    }
    public void updateById(PointGood pointGood){
        pointGoodMapper.updateByPrimaryKeySelective(pointGood);
    }

    @Override
    public void addPointGood(PointGood pointGood) {
        pointGoodMapper.insert(pointGood);
    }

    @Override
    public void deleteById(int id) {
        pointGoodMapper.deleteByPrimaryKey(id);
    }
    @Override
    public boolean BuyPointChangePoint(int pointGoodId,int userId){
        PointGood pointGood=pointGoodMapper.selectByPrimaryKey(pointGoodId);
        User user=userMapper.selectByPrimaryKey(userId);
        if(pointGood!=null&&user!=null){
            int userPoint=Integer.valueOf(user.getOther());
            int goodPoint=pointGood.getPoint();
            if(userPoint>goodPoint){
                return true;
            }else
                return false ;
        }
        return false;
    }

}
