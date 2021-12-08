package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.FlightSearch;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSearchParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("flight")
public class FlightController {

    @Resource
    private FlightSearch flightSearch;


    /**
     * 搜索指定的航班
     * @param flightSearchParams
     * @return
     */

    @PostMapping("search")
    public Result searchPlane(@RequestBody FlightSearchParams flightSearchParams){
        return flightSearch.searchPlane(flightSearchParams);
    }
}
