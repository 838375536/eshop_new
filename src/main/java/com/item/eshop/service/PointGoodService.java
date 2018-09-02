package com.item.eshop.service;

import com.item.eshop.model.PointGood;

import java.util.List;

public interface PointGoodService {
    List<PointGood> selectByQuery(String name, int status);
    List<PointGood> selectByQueryLimit(String name,int status,int page ,int num);

    PointGood getById(int id);
    void updateById(PointGood pointGood);

    void addPointGood(PointGood pointGood);

    void deleteById(int id);
    public boolean BuyPointChangePoint(int pointGoodId,int userId);
}
