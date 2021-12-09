package com.heyongqiang.work.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.PlaneMapper;
import com.heyongqiang.work.dao.mapper.TicketMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Planes;
import com.heyongqiang.work.dao.pojo.Ticket;
import com.heyongqiang.work.service.FlightSearch;
import com.heyongqiang.work.vo.FlightSearchVo;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSearchParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchImpl implements FlightSearch {

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private PlaneMapper planeMapper;

    private String day;


    /**
     * 搜索航班
     * @param flightSearchParams
     * @return
     */
    @Override
    public Result searchPlane(FlightSearchParams flightSearchParams) {
        /**
         * 首先搜索航班取出内部的所有指定的信息 然后一个一个 的 eq 到lam中 就可以了
         */
        String endCity = flightSearchParams.getEndCity();
        String beginCity = flightSearchParams.getBeginCity();
        day = flightSearchParams.getDay();
        LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isBlank(beginCity)){
            queryWrapper.eq(Flight::getBeginTime,beginCity);
        }
        if(!StringUtils.isBlank(endCity)){
            queryWrapper.eq(Flight::getEndCity,endCity);
        }
        List<Flight> flights = flightMapper.selectList(queryWrapper);

        return Result.success(copyList(flights));
    }

    /**
     * 通过日期查询 指定日期的航班
     * @param day
     * @return
     */

    @Override
    public Result searchPlaneDays(String day) {
        /**
         *  接收日期也会返回一个
         */



        return null;
    }

    /**
     * 状态转移
     * @param flights
     * @return
     */

    public List<FlightSearchVo> copyList(List<Flight> flights){
        List<FlightSearchVo> flightSearchVoList = new ArrayList<>();
        for (Flight params : flights) {
            flightSearchVoList.add(copy(params));
        }
        return flightSearchVoList;
    }

    public FlightSearchVo copy(Flight flight){
        FlightSearchVo flightSearchVo = new FlightSearchVo();
        BeanUtils.copyProperties(flight,flightSearchVo);
        LambdaQueryWrapper<Planes> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Planes::getPlaneId,flight.getPlaneId());
        Planes planes = planeMapper.selectOne(lambdaQueryWrapper);
//        起步价
        flightSearchVo.setLastPrice(flight.getEconomyPrice());
//        设置是否有 头等舱的票
        flightSearchVo.setIseconomy(SearchBooleanTicket(flight.getFlightId(), 0,planes.getEconomySeat()));
        flightSearchVo.setIsbusiness(SearchBooleanTicket(flight.getFlightId(), 1,planes.getBusinessSeat()));
        flightSearchVo.setIsfirst(SearchBooleanTicket(flight.getFlightId(), 2,planes.getFirstSeat()));
        return flightSearchVo;
    }

//    查询是否有对应舱的票
    private Boolean SearchBooleanTicket(Long flightId,Integer seat,Integer reSeat) {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Ticket::getSeat,seat);
        queryWrapper.eq(Ticket::getFlightId,flightId);
        queryWrapper.eq(Ticket::getTicketDay,day);
//        购买的票是否超过了本来有的表
        Integer byseat = ticketMapper.selectCount(queryWrapper);
        return byseat < reSeat ? false : true;
    }


}
