package com.example.demo.service;

import com.example.demo.entity.goodorder.GoodOrder;
import com.example.demo.entity.orderdetail.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    String createOrder(float price, int buyer_id, int seller_id, int good_id);
    List<GoodOrder> findMyTrading(int user_id);
    String continueTrade(int good_id);
    String finishTrade(int good_id);
}
