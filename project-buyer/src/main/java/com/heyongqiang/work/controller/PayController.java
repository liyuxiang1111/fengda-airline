package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.PayService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PageParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("pay")
@RestController
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("search")
    public Result searchPayList(@RequestBody PageParams pageParams){
        return payService.listPassengerPay(pageParams);
    }


}
