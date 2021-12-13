package com.heyongqiang.work.controller;


import com.heyongqiang.work.common.cache.Cache;
import com.heyongqiang.work.service.FlightIndexService;
import com.heyongqiang.work.service.FlightSearch;
import com.heyongqiang.work.service.FlightSearchDiscountService;
import com.heyongqiang.work.service.FlightSeatService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSearchParams;
import com.heyongqiang.work.vo.params.FlightSeatParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Resource
    private FlightSearch flightSearch;

    @Resource
    private FlightSeatService flightSeatService;

    @Resource
    private FlightSearchDiscountService flightSearchDiscountService;

    @Resource
    private FlightIndexService flightIndexService;


    /**
     * 搜索指定的航班
     * @param flightSearchParams
     * @return
     */

    @PostMapping("search")
    @Cache(expire = 60*60*12 ,name = "flight_search")
    public Result searchPlane(@RequestBody FlightSearchParams flightSearchParams){
        return flightSearch.searchPlane(flightSearchParams);
    }

    @GetMapping("search/discount")
    public Result searchTicketDiscount(){
        return flightSearchDiscountService.findTicketDiscount();
    }

    @PostMapping("seat")
    public Result searchBooleanSeat(@RequestBody FlightSeatParams flightSeatParams){
        return flightSeatService.searchBooleanSeat(flightSeatParams);
    }

    @GetMapping("index")
    public Result indexFlight(){
        return flightIndexService.searchIndexFlight();
    }


//    @PostMapping("search/{day}")
//    public Result searchPlaneDays(@PathVariable String day){
//        return flightSearch.searchPlaneDays(day);
//    }
}
