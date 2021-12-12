package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.PayService;
import com.heyongqiang.work.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("pay")
@RestController
public class PayController {

    @Resource
    private PayService payService;

    @GetMapping("search")
    public Result searchPayList(){
        return payService.listPassengerPay();
    }


}
