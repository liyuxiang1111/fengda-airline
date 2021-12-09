package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.LoginService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {



    @Autowired
    private LoginService loginService;

    /**
     *  用户登录
     */
    @PostMapping
    public Result login(@RequestBody LoginParams loginParams){
        return loginService.login(loginParams);
    }



}
