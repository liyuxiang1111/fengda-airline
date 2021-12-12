package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.PlaneMapper;
import com.heyongqiang.work.dao.mapper.TicketMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Ticket;
import com.heyongqiang.work.service.FlightSeatService;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.FlightBooleanParams;
import com.heyongqiang.work.vo.FlightSearchVo;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSeatParams;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;


@Service
public class FlightSeatServiceImpl implements FlightSeatService {

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private PlaneMapper planeMapper;

    @Override
    public Result searchBooleanSeat(FlightSeatParams flightSeatParams) {
        /**
         * 判断是否有座位
         *  1. 首先拿到参数 flightid
         *  2. 然后去数据库的ticket表查看所有的符合 tikcet flight seat 的数量
         *  3. 拿到本飞机的数据
         *  4. 进行运算计算出剩余的座位
         */
        Long flightId = flightSeatParams.getFlightId();
        if(flightId == null){
            return Result.fail(ErrorCode.MAIN_PARAMS_NULL.getCode(),ErrorCode.MAIN_PARAMS_NULL.getMsg());
        }
//        拿到flight的 数据
        Flight flight = flightMapper.selectById(flightSeatParams.getFlightId());
//        构建sql
        Integer economy =  countTicetBuyerNum(flight.getId(),0);
        Integer bussiness =  countTicetBuyerNum(flight.getId(),1);
        Integer first =  countTicetBuyerNum(flight.getId(),2);
        FlightBooleanParams flightBooleanParams = new FlightBooleanParams();
        flightBooleanParams.setIsbussiness(bussiness);
        flightBooleanParams.setIseconomy(economy);
        flightBooleanParams.setIsfirst(first);
        return Result.success(flightBooleanParams);
    }

    private Integer countTicetBuyerNum(Long flightId,Integer seat) {
        LambdaQueryWrapper<Ticket> queryWrapper = new LambdaQueryWrapper<>();


        queryWrapper.eq(Ticket::getFlightId,flightId);

        queryWrapper.eq(Ticket::getSeat,seat);

        return ticketMapper.selectCount(queryWrapper);

    }


}
