package com.heyongqiang.controller.controller;


import com.heyongqiang.controller.dao.pojo.Flight;
import com.heyongqiang.controller.dao.pojo.FlightDiscount;
import com.heyongqiang.controller.service.FlightService;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightDiscountParams;
import com.heyongqiang.controller.vo.params.FlightParams;
import com.heyongqiang.controller.vo.params.PageParams;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.data.redis.connection.ReactiveSubscription;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("discount")
    public Result setDiscountFlight(@RequestBody FlightDiscountParams flightDiscountParams){
        return flightService.setDiscountFlight(flightDiscountParams);
    }

    @PostMapping("change/information")
    public Result changeInformation(@RequestBody Flight flight){
        return flightService.changeInformation(flight);
    }

    @GetMapping("search")
    public Result searchAllFlight(@RequestBody PageParams pageParams){
        return flightService.findAllFlight(pageParams);
    }

}

