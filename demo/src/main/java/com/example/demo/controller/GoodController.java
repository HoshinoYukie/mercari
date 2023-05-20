package com.example.demo.controller;

import com.example.demo.entity.RestBean;
import com.example.demo.entity.good.Good;
import com.example.demo.service.GoodService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/good")
public class GoodController {

    @Resource
    GoodService service;

    @PostMapping("/upload-good")
    public RestBean<String> uploadGood(@RequestParam("name") String name,
                                       @RequestParam("description") String description,
                                       @RequestParam("price") float price,
                                       @RequestParam("seller_id") int seller_id){
        String s = service.uploadGoodWithoutImg(name, description, price, seller_id);
        if(s == null){
            return RestBean.success("上架成功");
        }
        else
            return RestBean.failure(400,s);
    }

    @PostMapping("/my-upload")
    public RestBean<List<Good>> findMyUpload(@RequestParam("seller_id") int seller_id){
        List<Good> goods = service.findMyUpload(seller_id);
        return RestBean.success(goods);
    }

    @PostMapping("/my-bought")
    public RestBean<List<Good>> findMyBought(@RequestParam("buyer_id") int buyer_id){
        List<Good> goods = service.findMyBought(buyer_id);
        return RestBean.success(goods);
    }

    @PostMapping("/my-sold")
    public RestBean<List<Good>> findMySold(@RequestParam("seller_id") int seller_id){
        List<Good> goods = service.findMySold(seller_id);
        return RestBean.success(goods);
    }

    @GetMapping("/{id}")
    public RestBean<Good> findGood(@PathVariable(value = "id") int good_id){
        Good good = service.findGood(good_id);
        return RestBean.success(good);
    }

    @PostMapping("/good-edit")
    public RestBean<String> editGood(@RequestParam("name") String name,
                                     @RequestParam("description") String description,
                                     @RequestParam("price") float price,
                                     @RequestParam("id") int id){
        String s = service.editGoodInfo(name, description, price, id);
        if(s == null){
            return RestBean.success("修改商品信息成功");
        }
        else
            return RestBean.failure(400,s);
    }
}
