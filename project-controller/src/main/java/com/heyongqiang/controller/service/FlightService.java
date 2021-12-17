package com.heyongqiang.controller.service;


import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightParams;

public interface FlightService {

    /**
     * 添加航班
     * @param flightParams
     * @return
     */
    Result addFlight(FlightParams flightParams);
}
