package com.example.demo.service;

import com.example.demo.entity.orderdetail.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    String createOrder(float price, int buyer_id, int seller_id, int good_id);
    List<OrderDetail> findMyTrading(int user_id);
}
