package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.PlaneMapper;
import com.heyongqiang.work.dao.mapper.TicketMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Plane;
import com.heyongqiang.work.dao.pojo.Ticket;
import com.heyongqiang.work.service.FlightSeatService;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.FlightBooleanVo;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.FlightSeatParams;
import org.springframework.stereotype.Service;

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
        Integer seats =  countTicetBuyerNum(flight,flightSeatParams.getSeat());
        FlightBooleanVo flightBooleanVo = new FlightBooleanVo();
        flightBooleanVo.setSeats(seats);
        return Result.success(flightBooleanVo);
    }

    private Integer countTicetBuyerNum(Flight flight,Integer seat) {
        LambdaQueryWrapper<Ticket> ticketWrapper = new LambdaQueryWrapper<>();

        ticketWrapper.eq(Ticket::getFlightId,flight.getId());

        ticketWrapper.eq(Ticket::getSeat,seat);
        Integer ticketCount = ticketMapper.selectCount(ticketWrapper);

        Plane plane = planeMapper.selectById(flight.getPlaneId());
        int planeSeat = 0;
        if(seat == 0){
            planeSeat = plane.getBusinessSeat();
        }
        if(seat == 1){
            planeSeat = plane.getEconomySeat();
        }
        if(seat == 2){
            planeSeat = plane.getFirstSeat();
        }

        return planeSeat - ticketCount;

    }


}
