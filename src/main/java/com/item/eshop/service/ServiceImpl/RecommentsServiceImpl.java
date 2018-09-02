package com.item.eshop.service.ServiceImpl;

import com.item.eshop.mapper.RecommentsMapper;
import com.item.eshop.service.RecommentsService;
import com.item.eshop.model.Recomments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommentsServiceImpl implements RecommentsService {
    @Autowired
    private RecommentsMapper RecommentsMapper;

    public void deleteByPrimaryKey(int id){
        RecommentsMapper.deleteByPrimaryKey(id);
    }
    public  void insert(Recomments recomments){ RecommentsMapper.insert(recomments);}
}
