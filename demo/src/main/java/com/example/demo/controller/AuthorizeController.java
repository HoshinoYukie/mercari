package com.example.demo.controller;

import com.example.demo.entity.RestBean;
import com.example.demo.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthorizeController {

    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%±]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$";

    @Resource
    AuthorizeService service;

    @PostMapping("/valid-email")
    public RestBean<String> validEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email){
        if(service.sendValidateEmail(email))
            return RestBean.success("验证码已发送，进入邮箱查看");
        else
            return RestBean.failure(400, "邮件发送失败，请联系管理员");
    }


}
