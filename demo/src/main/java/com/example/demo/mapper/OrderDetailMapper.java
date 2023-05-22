package com.example.demo.mapper;

import com.example.demo.entity.orderdetail.OrderDetail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Insert("insert into orderdetail (time, date, good_id, price, seller_id, buyer_id, status) value (#{time}, #{date}, #{good_id}, #{price}, #{seller_id}, #{buyer_id}, 0)")
    int createOrder(String time, String date, int good_id, float price, int seller_id, int buyer_id);

    @Select("select * from orderdetail where (seller_id = #{user_id} or buyer_id = #{user_id}) and status = 0")
    List<OrderDetail> findMyTrading(int user_id);
}
