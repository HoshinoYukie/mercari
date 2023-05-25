package com.example.demo.service.impl;

import com.example.demo.entity.goodorder.GoodOrder;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.service.OrderDetailService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderDetailService {

    @Resource
    OrderDetailMapper orderDetailMapper;

    @Resource
    GoodMapper goodMapper;

    @Override
    public String createOrder(float price, int buyer_id, int seller_id, int good_id) {
        if( goodMapper.checkGoodIsOnSelling(good_id).size() > 0 ){
            if( goodMapper.goodStatusChangeToSold(good_id) > 0 ){
            LocalDate d = LocalDate.now();
            DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalTime t  = LocalTime.now();
            DateTimeFormatter tFormatter = DateTimeFormatter.ofPattern("HH:mm");
            int i = orderDetailMapper.createOrder(t.format(tFormatter),d.format(dFormatter),good_id,price,seller_id,buyer_id);
            if( i > 0 )
                return null;
            else
                return "交易建立失败";
            } else {
                return "交易以非正常方式结束";
            }
        } else {
            return "商品已被其他买家购买";
        }
    }

    @Override
    public List<GoodOrder> findMyTrading(int user_id){
        return orderDetailMapper.findMyTrading(user_id);
    }

    @Override
    public String continueTrade(int good_id){
        if( goodMapper.continueTrade(good_id) > 0 && orderDetailMapper.continueTrade(good_id) > 0 )
            return null;
        else
            return "该商品不存在";
    }

    @Override
    public String finishTrade(int good_id){
        if( goodMapper.finishTrade(good_id) > 0 && orderDetailMapper.finishTrade(good_id) > 0 )
            return null;
        else
            return "该商品不存在";
    }
}
