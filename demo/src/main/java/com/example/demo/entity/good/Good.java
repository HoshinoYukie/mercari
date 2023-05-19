package com.example.demo.entity.good;

import lombok.Data;

@Data
public class Good {
    int id;
    String name;
    float price;
    String description;
    String img_path;
}
