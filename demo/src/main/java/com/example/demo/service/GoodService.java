package com.example.demo.service;

import com.example.demo.entity.good.Good;

import java.util.List;

public interface GoodService {
    String uploadImg(String img_path);
    String uploadGoodWithoutImg(String name, String description, float price, int seller_id);
    List<Good> findMyUpload(int seller_id);
    List<Good> findMyBought(int buyer_id);
    List<Good> findMySold(int seller_id);
    Good findGood(int good_id);
    String editGoodInfo(String name, String description, float price, int good_id);
    String deleteGood(int good_id);
    List<Good> searchGoodsByKey(String searchKey);
    List<Good> showGoods();
}
