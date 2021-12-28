package com.heyongqiang.work.controller;


import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.service.PassengerService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;




    @GetMapping
    public Result getuserInformation(HttpServletRequest request){
        return passengerService.getUserInformation(request);
    }

    @PostMapping("informations")
    public Result changeUserInformation(HttpServletRequest request,@RequestBody PassengerChangeParams passengerChangeParams){
        return passengerService.changeUserInformation(passengerChangeParams,request);
    }

    @PostMapping("password")
    public Result changePassword(@RequestBody PassengerPasswordParams passengerPasswordParams){
        return passengerService.changePassengerPwd(passengerPasswordParams);
    }



}
