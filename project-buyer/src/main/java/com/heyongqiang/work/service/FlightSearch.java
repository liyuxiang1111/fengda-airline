package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSearchParams;


public interface FlightSearch {
    Result searchPlane(FlightSearchParams flightSearchParams);
}
