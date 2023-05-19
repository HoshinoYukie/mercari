package com.example.demo.controller;

import com.example.demo.entity.good.Good;
import com.example.demo.entity.user.AccountUser;
import com.example.demo.entity.RestBean;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/me")
    public RestBean<AccountUser> me(@SessionAttribute("account") AccountUser user){
        return RestBean.success(user);
    }

}
