package com.example.demo.controller;

import com.example.demo.entity.RestBean;
import com.example.demo.entity.good.Good;
import com.example.demo.entity.orderdetail.OrderDetail;
import com.example.demo.mapper.OrderDetailMapper;
import com.example.demo.service.GoodService;
import com.example.demo.service.OrderDetailService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/order")
public class OrderDetailController {

    @Resource
    OrderDetailService service;

    @PostMapping("/buy")
    public RestBean<String> buyGood(@RequestParam("price") float price,
                                    @RequestParam("seller_id") int seller_id,
                                    @RequestParam("buyer_id") int buyer_id,
                                    @RequestParam("good_id") int good_id){
        String s = service.createOrder(price, seller_id, buyer_id, good_id);
        if( s == null ){
            return RestBean.success("交易已建立，请及时与卖家联系");
        } else
            return RestBean.failure(400,s);
    }

    @GetMapping("/my-trading/{user_id}")
    public RestBean<List<OrderDetail>> findMyTrading(@PathVariable(value = "user_id") int user_id){
        return RestBean.success(service.findMyTrading(user_id));
    }
}
