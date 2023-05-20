package com.example.demo.mapper;

import com.example.demo.entity.good.Good;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodMapper {
    @Insert("insert into good (name, price, description, seller_id, img_path, auditing) value (#{name}, #{price}, #{description}, #{seller_id}, #{img_path}, 0)")
    int uploadGood(String name, float price, String description, int seller_id, String img_path);

    @Insert("insert into good (name, price, description, seller_id, auditing) value (#{name}, #{price}, #{description}, #{seller_id}, 0)")
    int uploadGoodWithoutImg(String name, float price, String description, int seller_id);

    @Select("select * from good where seller_id = #{seller_id}")
    List<Good> findMyUploadGood(int seller_id);

    @Select("select * from good,(select good_id from orderdetail where buyer_id = #{buyer_id}) a where good.id = a.good_id")
    List<Good> findMyBoughtGood(int buyer_id);

    @Select("select * from good,(select good_id from orderdetail where seller_id = #{seller_id}) a where good.id = a.good_id")
    List<Good> findMySoldGood(int seller_id);

    @Select("select * from good where id = #{good_id}")
    Good findGood(int good_id);

    @Update("update good set name = #{name}, description = #{description}, price = #{price} where id = #{id}")
    int editGoodWithoutImg(String name, String description, float price, int id);
}
