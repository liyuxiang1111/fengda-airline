package com.heyongqiang.controller.controller;

import com.heyongqiang.controller.service.PassengerService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("passenger")
public class PassengerController {

    @Resource
    private PassengerService passengerService;

    @GetMapping("searchall")
    public Result searchAllPassenger(){
        return passengerService.searchAllPassenger();
    }

    @PostMapping("role")
    public Result changeUserRole(@RequestParam String passengerId){
        return passengerService.changeRole(passengerId);
    }


}
