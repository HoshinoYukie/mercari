package com.example.demo.mapper;

import com.example.demo.entity.goodorder.GoodOrder;
import com.example.demo.entity.orderdetail.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into orderdetail (time, date, good_id, price, seller_id, buyer_id, status) value (#{time}, #{date}, #{good_id}, #{price}, #{seller_id}, #{buyer_id}, 1)")
    int createOrder(String time, String date, int good_id, float price, int seller_id, int buyer_id);

    @Select("select o.id,o.time,o.date,o.good_id,o.price,o.seller_id,o.buyer_id,o.status,good.name from orderdetail o left join good on good_id = good.id where (o.seller_id = #{user_id} or buyer_id = #{user_id}) and o.status = 1 or o.status = 2;")
    List<GoodOrder> findMyTrading(int user_id);

    @Update("update orderdetail set status = 2 where good_id = #{good_id}")
    int continueTrade(int good_id);

    @Update("update orderdetail set status = 3 where good_id = #{good_id}")
    int finishTrade(int good_id);
}
