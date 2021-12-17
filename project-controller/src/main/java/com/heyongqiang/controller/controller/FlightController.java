package com.heyongqiang.controller.controller;


import com.heyongqiang.controller.service.FlightService;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightParams;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Resource
    private FlightService flightService;

    @PostMapping("add")
    public Result addFlight(@RequestBody FlightParams flightParams){
        return flightService.addFlight(flightParams);
    }

}
