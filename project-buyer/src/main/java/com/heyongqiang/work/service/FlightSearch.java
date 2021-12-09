package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSearchParams;


public interface FlightSearch {

    /**
     * 查询指定的 出发地 到 目的地
     * @param flightSearchParams
     * @return
     */

    Result searchPlane(FlightSearchParams flightSearchParams);

    /**
     * 给一个日期查询指定日期的航班
     * @param day
     * @return
     */

    Result searchPlaneDays(String day);
}
