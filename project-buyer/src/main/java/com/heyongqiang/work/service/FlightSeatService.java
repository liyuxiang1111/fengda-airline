package com.heyongqiang.work.service;


import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSeatParams;

public interface FlightSeatService {
    /**
     * 检查是否有座位  而且座位数量是多少
     * @param flightSeatParams
     * @return
     */

    Result searchBooleanSeat(FlightSeatParams flightSeatParams);
}
