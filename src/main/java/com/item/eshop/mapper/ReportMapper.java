package com.item.eshop.mapper;

import com.item.eshop.model.Status;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportMapper {

    int countUser();
    List<Status> countUserByStatus();
    int countOrder(int shopId);
    List<Status> countOrderByStatus(int shopId);
    int countGood(int shopId);
    List<Status> countGoodByStatus(int shopId);
    Double countAmountSum(int shopId);
    List<Status> countOrderByStatusAndShop();
    List<Status> countGoodByStatusAndShop();
    double countAmountSumByShop();
    List<Status> countAmountSumByStatus();
}
