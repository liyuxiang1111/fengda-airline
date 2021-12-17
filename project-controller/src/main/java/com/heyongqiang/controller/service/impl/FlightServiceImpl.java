package com.heyongqiang.controller.service.impl;

import com.heyongqiang.controller.dao.mapper.FlightMapper;
import com.heyongqiang.controller.dao.mapper.PlaneMapper;
import com.heyongqiang.controller.dao.pojo.Flight;
import com.heyongqiang.controller.dao.pojo.Plane;
import com.heyongqiang.controller.service.FlightService;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FlightServiceImpl implements FlightService {


    @Resource
    private FlightMapper flightMapper;

    @Override
    public Result addFlight(FlightParams flightParams) {
        /**
         * 添加航班首先要知道飞机的基本信息 和航班基本信息
         */
        String planeId = flightParams.getPlaneId();
        Flight flight = new Flight();
        flight.setBeginCity(flightParams.getBeginCity());
        flight.setEndCity(flightParams.getEndCity());
        flight.setBeginTime(flightParams.getBeginTime());
        flight.setEndTime(flightParams.getEndTime());
        flight.setBusinessPrice(flightParams.getBusinessPrice().toString());
        flight.setEconomyPrice(flightParams.getEconomyPrice().toString());
        flight.setFirstPrice(flightParams.getFirstPrice().toString());
        flight.setFlightName(flightParams.getFlightName());
        flight.setPlaneId(Long.parseLong(flightParams.getPlaneId()));
        int insert = flightMapper.insert(flight);
        if(insert == 0){
            return Result.fail(202,"数据库更新失败");
        }
        return Result.success(null);
    }
}
