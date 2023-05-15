package com.example.demo.service.Impl;

import com.example.demo.entity.Account;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthorizeServiceImpl implements AuthorizeService {

    @Value("${spring.mail.username}")
    String from;

    @Resource
    UserMapper mapper;

    @Resource
    MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if( username == null )
            throw new UsernameNotFoundException("用户名或邮箱不能为空");
        Account account = mapper.findAccountCountOrEmail(username);
        if( account == null )
            throw new UsernameNotFoundException("用户名或密码错误");
        return User
                .withUsername(account.getUsername())
                .password(account.getPassword())
                .roles("user")
                .build();
    }

    @Override
    public boolean sendValidateEmail(String email){
        /**
         *  1.生成对应验证码
         *  2.把邮箱和对应验证码放入 Radis 里，过期时间 3 分钟，如果此时重新要求，若剩余时间低于 2 分钟 重复此流程
         *  3.发送验证码到指定邮箱
         *  4.发送失败，把 Radis 里插入的数据删除
         *  5.用户注册时，再把 Radis 里面取出键值对，看验证码是否一致
         */
        Random random = new Random();
        int code = random.nextInt(89999) + 10000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(email);
        message.setSubject("物物的注册验证邮件");
        message.setText("验证码是：" + code);
        try {
            mailSender.send(message);
            return true;
        } catch (MailException e) {
            e.printStackTrace();
            return false;
        }

    }
}
