package com.example.demo.controller;

import com.example.demo.entity.RestBean;
import com.example.demo.entity.good.Good;
import com.example.demo.service.GoodService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/good")
public class GoodController {

    private final String[] imageExtension = new String[]{"jpeg", "jpg", "bmp", "png"};

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private boolean isImage(String fileName){
        String extension = getFileExtension(fileName);
        for (String e : imageExtension){
            if (extension.toLowerCase().equals(e)) {
                return true;
            }
        }

        return false;
    }

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

    @GetMapping("/delete/{id}")
    public RestBean<String> deleteGood(@PathVariable(value = "id") int good_id){
        String s = service.deleteGood(good_id);
        if(s == null){
            return RestBean.success("物品成功下架");
        }
        else
            return RestBean.failure(400,s);
    }

    @GetMapping("/search/{key}")
    public RestBean<List<Good>> searchGoodsByKey(@PathVariable( value = "key" ) String key){
        List<Good> goods = service.searchGoodsByKey("%"+key+"%");
        return RestBean.success(goods);
    }

    @GetMapping("/search")
    public RestBean<List<Good>> showGoods(){
        List<Good> goods = service.showGoods();
        return RestBean.success(goods);
    }

    @PostMapping("/uploadimg")
    public RestBean<String> uploadImg(MultipartFile mFile) {
        String folder = "D:/mercari/file/img";
        File imgFolder = new File(folder);
        String fname = mFile.getOriginalFilename();
        if( !isImage(fname) )
            return RestBean.failure(400,"仅允许图片上传");
        else {
            String ext = "." + fname.substring(fname.lastIndexOf(".") + 1);
            String dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm:ss").format(LocalDateTime.now());
            String newFileName = dateTimeFormatter + UUID.randomUUID() + ext;
            File filePath = new File(imgFolder, newFileName);
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            try{
                mFile.transferTo(filePath);
                // 返回文件名
                return RestBean.success(filePath.getName());
            }catch (IOException e){
                e.printStackTrace();
                return RestBean.failure(400,"在上传图片时发生错误");
            }
        }
    }
}
