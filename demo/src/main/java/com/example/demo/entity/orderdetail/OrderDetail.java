package com.example.demo.entity.orderdetail;

import lombok.Data;

@Data
public class OrderDetail {
    int id;
    String time;
    String date;
    int goodId;
    float price;
    int sellerId;
    int buyerId;
    int status;
}
