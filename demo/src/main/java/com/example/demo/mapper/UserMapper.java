package com.example.demo.mapper;

import com.example.demo.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper{
    @Select("select * from user where username = #{text} or email = #{text}")
    Account findAccountCountOrEmail(String text);
}
