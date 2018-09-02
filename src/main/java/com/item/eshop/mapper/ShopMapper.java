package com.item.eshop.mapper;

import com.item.eshop.model.Shop;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    List<Shop> selectMore(Integer page, Integer num);

    List<Shop> selectMoreByCategory(Integer valid, Integer page, Integer num);

    Shop selectByUser(Integer id);
    //add zheng 2018.6.30
    List<Shop> selectShopBySchoolId(Integer id);

    Shop getById(int id);

    //----------

    List<Shop> selectShopByQuery(@Param("shopname") String name, @Param("status") int status);

    List<Shop> getAllLimit(@Param("page") int page,@Param("num") int num);

    List<Shop> getAll();

    List<Shop> selectShopByQueryLimit(@Param("shopname") String name,@Param("status") int status, @Param("page")int page,@Param("num")  int num);
}