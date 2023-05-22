package com.example.demo.entity.good;

import lombok.Data;

@Data
public class Good {
    int id;
    String name;
    float price;
    String description;
    int sellerId;
    String imgPath;
    int auditing;
    int status;
}
