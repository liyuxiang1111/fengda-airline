package com.heyongqiang.work.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.TicketReturnMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.dao.pojo.TicketReturn;
import com.heyongqiang.work.service.TicketSearchReturnService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.TicketReturnVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketSearchReturnServiceImpl implements TicketSearchReturnService {

    @Resource
    private TicketReturnMapper ticketReturnMapper;

    @Resource
    private FlightMapper flightMapper;


    @Override
    public Result findTicketReturn() {
        Passenger passenger = UserThreadLocal.get();
        Long id = passenger.getId();
        LambdaQueryWrapper<TicketReturn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketReturn::getUserId,id);
        List<TicketReturn> ticketReturns = ticketReturnMapper.selectList(queryWrapper);
        return Result.success(copyList(ticketReturns));
    }



    /**
     * 状态转移   ticketReturn
     * @param tickets
     * @return
     */

    public List<TicketReturnVo> copyList(List<TicketReturn> tickets){
        List<TicketReturnVo> ticketReturnVoList = new ArrayList<>();
        for (TicketReturn params : tickets) {
            ticketReturnVoList.add(copy(params));
        }
        return ticketReturnVoList;
    }

    public TicketReturnVo copy(TicketReturn ticket){
        TicketReturnVo ticketReturnVo = new TicketReturnVo();
//        工具copy
        Flight flight = flightMapper.selectById(ticket.getFlightId());
        BeanUtils.copyProperties(flight,ticketReturnVo);
        ticketReturnVo.setIsCompute(ticket.getIswatch());
        ticketReturnVo.setPrice(ticket.getTicketPrice());
        ticketReturnVo.setTicketId(ticket.getTicketId());
//        得到指定的航班 set 对应属性
        return ticketReturnVo;
    }


}
