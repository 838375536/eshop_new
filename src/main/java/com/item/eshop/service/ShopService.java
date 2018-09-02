package com.item.eshop.service;

import com.item.eshop.model.Notice;
import com.item.eshop.model.School;
import com.item.eshop.model.Shop;

import java.util.List;

public interface ShopService {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<Shop> selectMore(Integer page, Integer num);

    List<Shop> selectMoreByCategory(Integer valid, Integer page, Integer num);

    Shop selectByUser(Integer id);


    //---------------------
    List<School> selectAllSchool();
    Shop getById(int id);
    List<Shop> getAllLimit(int i, int num);

    List<Shop> getAll();

    List<Shop> selectShopByQuery(String name, int status);

    List<Shop> selectShopByQueryLimit(String name, int status, int i, int num);
}
