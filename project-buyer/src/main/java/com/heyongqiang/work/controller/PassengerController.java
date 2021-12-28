package com.heyongqiang.work.controller;


import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.service.PassengerService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;




    @GetMapping
    public Result getuserInformation(){
        return passengerService.getUserInformation();
    }

    @PostMapping("informations")
    public Result changeUserInformation(@RequestBody PassengerChangeParams passengerChangeParams){
        return passengerService.changeUserInformation(passengerChangeParams);
    }

    @PostMapping("password")
    public Result changePassword(@RequestBody PassengerPasswordParams passengerPasswordParams,@RequestHeader("Authorization") String token){
        return passengerService.changePassengerPwd(passengerPasswordParams,token);
    }



}
