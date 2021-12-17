package com.heyongqiang.controller.controller;

import com.heyongqiang.controller.service.LoginService;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.LoginParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("login")
public class LoginController {

    @Resource
    private LoginService loginService;


    @PostMapping
    public Result controllerLogin(@RequestBody LoginParams loginParams){
        return loginService.login(loginParams);
    }

}
