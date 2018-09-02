package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.NoticeMapper;
import com.item.eshop.mapper.SchoolMapper;
import com.item.eshop.mapper.ShopMapper;
import com.item.eshop.model.Notice;
import com.item.eshop.model.School;
import com.item.eshop.model.Shop;
import com.item.eshop.service.NoticeService;
import com.item.eshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "ShopService")
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    SchoolMapper schoolMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return shopMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Shop record) {
        return shopMapper.insert(record);
    }

    @Override
    public int insertSelective(Shop record) {
        return shopMapper.insertSelective(record);
    }

    @Override
    public Shop selectByPrimaryKey(Integer id) {
        return shopMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Shop record) {
        return shopMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Shop record) {
        return shopMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<Shop> selectMore(Integer page, Integer num) {
        return shopMapper.selectMore(page,num);
    }

    @Override
    public List<Shop> selectMoreByCategory(Integer valid, Integer page, Integer num) {
        return shopMapper.selectMoreByCategory(valid,page,num);
    }

    @Override
    public Shop selectByUser(Integer id) {
        return shopMapper.selectByUser(id);
    }

    //-------------------

    @Override
    public List<School> selectAllSchool() {
        return schoolMapper.selectAllSchool();
    }

    @Override
    public Shop getById(int id) {
        return shopMapper.getById(id);
    }

    @Override
    public List<Shop> getAllLimit(int page, int num) {
        return shopMapper.getAllLimit(page,num);
    }

    @Override
    public List<Shop> getAll() {
        return shopMapper.getAll();
    }

    @Override
    public List<Shop> selectShopByQuery(String name, int status) {
        return shopMapper.selectShopByQuery(name,status);
    }

    @Override
    public List<Shop> selectShopByQueryLimit(String name,int status,int page,int num) {
        return shopMapper.selectShopByQueryLimit(name,status,page,num);
    }


}
