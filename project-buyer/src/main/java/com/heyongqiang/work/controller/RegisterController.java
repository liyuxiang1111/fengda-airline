package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.RegisterService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import com.heyongqiang.work.vo.params.PassengerRegisterParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping
    public Result registerPassenger(@RequestBody PassengerRegisterParams registerParams){
        return registerService.registerPassenger(registerParams);
    }

}
