package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.PassengerService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;


    @PostMapping("informations")
    public Result changeUserInformation(@RequestBody PassengerChangeParams passengerChangeParams){
        return passengerService.changeUserInformation(passengerChangeParams);
    }

    @PostMapping("pwssword")
    public Result changePassword(@RequestParam PassengerPasswordParams passengerPasswordParams){
        return passengerService.changePassengerPwd(passengerPasswordParams);
    }

}
