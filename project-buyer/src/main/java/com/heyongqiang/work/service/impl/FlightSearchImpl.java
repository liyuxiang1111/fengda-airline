package com.heyongqiang.work.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.common.cache.Cache;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.PlaneMapper;
import com.heyongqiang.work.dao.mapper.TicketMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Plane;
import com.heyongqiang.work.dao.pojo.Ticket;
import com.heyongqiang.work.service.FlightSearch;
import com.heyongqiang.work.vo.FlightSearchVo;
import com.heyongqiang.work.vo.Page;
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
        int pageSize = flightSearchParams.getPageSize();
        int pageNum = flightSearchParams.getPageNum();
//        查询数量
        LambdaQueryWrapper<Flight> selectCount = new LambdaQueryWrapper<>();
        selectCount.eq(Flight::getBeginCity,beginCity);
        selectCount.eq(Flight::getEndCity,endCity);
        Integer count = flightMapper.selectCount(selectCount);

        List<Flight> flights = flightMapper.selectPlansLimit(beginCity,endCity,pageNum,pageSize);

        List<FlightSearchVo> flightSearchVoList = copyList(flights);
        Page<FlightSearchVo> page = new Page<FlightSearchVo>(flightSearchParams.getPageNum(),flightSearchParams.getPageSize(),count);
        page.setDataList(flightSearchVoList);
        return Result.success(page);
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
        /**
         * 根据  飞机的型号 拿到飞机的基本信息
         */
        flightSearchVo.setFlightId(String.valueOf(flight.getId()));
//        起步价
        flightSearchVo.setLastPrice(Integer.parseInt(flight.getEconomyPrice()));
        flightSearchVo.setBusinessPrice(Integer.parseInt(flight.getBusinessPrice()));
        flightSearchVo.setEconomyPrice(Integer.parseInt(flight.getEconomyPrice()));
        flightSearchVo.setFirstPrice(Integer.parseInt(flight.getFirstPrice()));
        flightSearchVo.setStatus(false);

        return flightSearchVo;
    }



}
