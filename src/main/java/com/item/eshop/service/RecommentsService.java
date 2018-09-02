package com.item.eshop.service;

import com.item.eshop.model.Recomments;

public interface RecommentsService {
    void deleteByPrimaryKey(int id);
    public  void insert(Recomments recomments);
}
