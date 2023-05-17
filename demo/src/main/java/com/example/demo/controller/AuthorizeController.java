package com.example.demo.controller;

import com.example.demo.entity.RestBean;
import com.example.demo.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
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
    private final String USERNAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9_-]+$";

    @Resource
    AuthorizeService service;

    @PostMapping("/valid-create-email")
    public RestBean<String> validEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                       HttpSession session){
        String s = service.sendValidateEmail(email, session.getId(), false);
        if(s == null)
            return RestBean.success("验证码已发送，进入邮箱查看");
        else
            return RestBean.failure(400, s);
    }

    @PostMapping("/valid-reset-email")
    public RestBean<String> validateResetEmail(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                               HttpSession session){
        String s = service.sendValidateEmail(email, session.getId(), true);
        if (s == null)
            return RestBean.success("验证码已发送，进入邮箱查看");
        else
            return RestBean.failure(400, s);
    }

    @PostMapping("/register")
    public RestBean<String> registerUser(@Pattern(regexp = USERNAME_REGEX) @Length(min = 2, max = 10) @RequestParam("username") String username,
                                         @Length(min = 6) @RequestParam("password") String password,
                                         @Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                         @Length(min = 5, max = 5) @RequestParam("code") String code,
                                         HttpSession session) {
        String s = service.validateAndRegister(username, password, email, code, session.getId());
        if(s == null)
            return RestBean.success("注册成功");
        else
            return RestBean.failure(400, s);
    }

    @PostMapping("/start-reset")
    public RestBean<String> startReset(@Pattern(regexp = EMAIL_REGEX) @RequestParam("email") String email,
                                       @Length(min = 5, max = 5) @RequestParam("code") String code,
                                       HttpSession session) {
        String s = service.validateOnly(email, code, session.getId());
        if(s == null) {
            session.setAttribute("reset-password", email);
            return RestBean.success();
        } else {
            return RestBean.failure(400, s);
        }
    }

    @PostMapping("/do-reset")
    public RestBean<String> resetPassword(@Length(min = 6) @RequestParam("password") String password,
                                          HttpSession session){
        String email = (String) session.getAttribute("reset-password");
        if(email == null) {
            return RestBean.failure(401, "请先完成邮箱验证");
        } else if(service.resetPassword(password, email)){
            session.removeAttribute("reset-password");
            return RestBean.success("密码重置成功");
        } else {
            return RestBean.failure(500, "内部错误，请联系管理员");
        }
    }
}
