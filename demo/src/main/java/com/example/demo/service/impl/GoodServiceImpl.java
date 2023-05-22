package com.example.demo.service.impl;

import com.example.demo.entity.good.Good;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Resource
    GoodMapper mapper;

    @Override
    public String uploadImg(String img_path){
        if( img_path == null )
            return "上传的图片路径为空";
        return null;
    }

    @Override
    public String uploadGoodWithoutImg(String name, String description, float price, int seller_id){
        if(mapper.uploadGoodWithoutImg(name, price, description, seller_id) > 0){
            return null;
        }
        else{
            return "上架时发生错误";
        }
    }

    @Override
    public List<Good> findMyUpload(int seller_id){
        List<Good> goods = mapper.findMyUploadGood(seller_id);
        int num = goods.size();
        if( num == 0 )
            return null;
        else
            return goods;
    }

    @Override
    public List<Good> findMyBought(int buyer_id){
        List<Good> goods = mapper.findMyBoughtGood(buyer_id);
        int num = goods.size();
        if( num == 0 )
            return null;
        else
            return goods;
    }

    @Override
    public List<Good> findMySold(int seller_id){
        List<Good> goods = mapper.findMySoldGood(seller_id);
        int num = goods.size();
        if( num == 0 )
            return null;
        else
            return goods;
    }

    @Override
    public Good findGood(int good_id){
        return mapper.findGood(good_id);
    }

    @Override
    public String editGoodInfo(String name, String description, float price, int good_id){
        if(mapper.editGoodWithoutImg(name, description, price, good_id) > 0){
            return null;
        }
        else{
            return "修改信息时发生错误";
        }
    }

    @Override
    public String deleteGood(int good_id){
        if(mapper.deleteGood(good_id) > 0){
            return null;
        }
        else{
            return "下架时发生错误";
        }
    }

    @Override
    public List<Good> searchGoodsByKey(String key){
        List<Good> goods = mapper.searchGoodsByKey(key);
        return goods;
    }

    @Override
    public List<Good> showGoods(){
        List<Good> goods = mapper.showGoods();
        return goods;
    }
}
