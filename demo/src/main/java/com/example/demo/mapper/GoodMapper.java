package com.example.demo.mapper;

import com.example.demo.entity.good.Good;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodMapper {
    @Insert("insert into good (name, price, description, seller_id, img_path, auditing) value (#{name}, #{price}, #{description}, #{seller_id}, #{img_path}, 0)")
    int uploadGood(String name, float price, String description, int seller_id, String img_path);

    @Insert("insert into good (name, price, description, seller_id, auditing, status) value (#{name}, #{price}, #{description}, #{seller_id}, 0, 0)")
    int uploadGoodWithoutImg(String name, float price, String description, int seller_id);

    @Select("select * from good where seller_id = #{seller_id} and status = 0")
    List<Good> findMyUploadGood(int seller_id);

    @Select("select * from good where id = #{good_id} and status = 0")
    List<Good> checkGoodIsOnSelling(int good_id);

    @Update("update good set status = 1 where id = #{good_id}")
    int goodStatusChangeToSold(int good_id);

    @Select("select * from good,(select good_id from orderdetail where buyer_id = #{buyer_id} and status = 2) a where good.id = a.good_id")
    List<Good> findMyBoughtGood(int buyer_id);

    @Select("select * from good,(select good_id from orderdetail where seller_id = #{seller_id} and status = 2) a where good.id = a.good_id")
    List<Good> findMySoldGood(int seller_id);

    @Select("select * from good where id = #{good_id}")
    Good findGood(int good_id);

    @Update("update good set name = #{name}, description = #{description}, price = #{price} where id = #{id}")
    int editGoodWithoutImg(String name, String description, float price, int id);

    @Delete("delete from good where id = #{id}")
    int deleteGood(int id);

    @Select("select * from good where name like #{key} and status = 0")
    List<Good> searchGoodsByKey(String key);

    @Select("select * from good where status = 0")
    List<Good> showGoods();
}
