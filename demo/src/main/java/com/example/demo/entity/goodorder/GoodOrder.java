package com.example.demo.entity.goodorder;

import lombok.Data;

@Data
public class GoodOrder {
    int id;
    String time;
    String date;
    int goodId;
    float price;
    int sellerId;
    int buyerId;
    int status;
    String name;
}
