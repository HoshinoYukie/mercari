package com.example.demo.mapper;

import com.example.demo.entity.good.Good;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodMapper {
    @Insert("insert into good (name, price, description, seller_id, img_path, auditing) value (#{name}, #{price}, #{description}, #{seller_id}, #{img_path}, 0)")
    int uploadGood(String name, float price, String description, int seller_id, String img_path);

    @Insert("insert into good (name, price, description, seller_id, auditing) value (#{name}, #{price}, #{description}, #{seller_id}, 0)")
    int uploadGoodWithoutImg(String name, float price, String description, int seller_id);

    @Select("select * from good where seller_id = #{seller_id}")
    List<Good> findMyUploadGood(int seller_id);
}
