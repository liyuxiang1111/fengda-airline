package com.heyongqiang.controller.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.controller.dao.mapper.FlightDiscountMapper;
import com.heyongqiang.controller.dao.mapper.FlightMapper;
import com.heyongqiang.controller.dao.mapper.PlaneMapper;
import com.heyongqiang.controller.dao.pojo.Flight;
import com.heyongqiang.controller.dao.pojo.FlightDiscount;
import com.heyongqiang.controller.dao.pojo.Plane;
import com.heyongqiang.controller.service.FlightService;
import com.heyongqiang.controller.vo.Page;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightDiscountParams;
import com.heyongqiang.controller.vo.params.FlightParams;
import com.heyongqiang.controller.vo.params.PageParams;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {


    @Resource
    private FlightMapper flightMapper;

    @Resource
    private FlightDiscountMapper flightDiscountMapper;

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

    @Override
    public Result setDiscountFlight(FlightDiscountParams flightDiscountParams) {
        /**
         * 直接插入 disoucnt表就可以了
         */
        FlightDiscount flightDiscount = new FlightDiscount();
        flightDiscount.setDiscount(flightDiscountParams.getDiscount());
        flightDiscount.setFlightId(flightDiscountParams.getFlightId());
        flightDiscount.setDiscountTime(System.currentTimeMillis());
        int insert = flightDiscountMapper.insert(flightDiscount);
        if(insert == 0){
            return Result.fail(202,"数据库更新失败");
        }
        return Result.success(null);
    }

    @Override
    public Result changeInformation(Flight flight) {
        /**
         *  直接update
         */
        flightMapper.updateById(flight);
        return Result.success(null);
    }

    @Override
    public Result findAllFlight(PageParams pageParams) {

        //        查询数量
        Integer count = flightMapper.selectCount(null);
        Page<Flight> flightPage = new Page<>(pageParams.getPageNum(), pageParams.getPageNum(), count);

        LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.last(" limit "+ pageParams.getPageNum() + " , " + pageParams.getPageSize());
        List<Flight> flights = flightMapper.selectList(queryWrapper);
        flightPage.setDataList(flights);
        return Result.success(flightPage);
    }


}
