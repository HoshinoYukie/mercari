package com.example.demo.service;

import com.example.demo.entity.good.Good;

import java.util.List;

public interface GoodService {
    String uploadImg(String img_path);
    String uploadGoodWithoutImg(String name, String description, float price, int seller_id);
    List<Good> findMyUpload(int seller_id);
}
