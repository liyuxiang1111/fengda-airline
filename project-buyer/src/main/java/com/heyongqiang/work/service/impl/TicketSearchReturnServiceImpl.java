package com.heyongqiang.work.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.*;
import com.heyongqiang.work.dao.pojo.*;
import com.heyongqiang.work.service.TicketSearchReturnService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.Page;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.TicketReturnVo;
import com.heyongqiang.work.vo.params.PageParams;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketSearchReturnServiceImpl implements TicketSearchReturnService {

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private BuyerMapper buyerMapper;

    @Resource
    private PayMapper payMapper;

    @Resource
    private TicketReturnMapper ticketReturnMapper;


    @Override
    public Result findTicketReturn(PageParams pageParams) {

        Passenger passenger = UserThreadLocal.get();
        Long id = passenger.getId();
//        查看订单表中的用户id 为这个用户 的所有订单list
        List<String> ticketIdList = payMapper.selectTicketIdList(id);
        Page<TicketReturnVo> page = new Page<>(pageParams.getPageNum(),pageParams.getPageSize(),ticketIdList.size());
//        机票列表
        List<Ticket> ticketList =  ticketMapper.findTicketListReturn(ticketIdList,pageParams.getPageNum(),pageParams.getPageSize());

        page.setDataList(copyList(ticketList));
        return Result.success(page);
    }



    /**
     * 状态转移   ticketReturn
     * @param tickets
     * @return
     */

    public List<TicketReturnVo> copyList(List<Ticket> tickets){
        List<TicketReturnVo> ticketReturnVoList = new ArrayList<>();
        for (Ticket params : tickets) {
            ticketReturnVoList.add(copy(params));
        }
        return ticketReturnVoList;
    }

    public TicketReturnVo copy(Ticket ticket){
        TicketReturnVo ticketReturnVo = new TicketReturnVo();
//        工具copy
        Flight flight = flightMapper.selectById(ticket.getFlightId());
        BeanUtils.copyProperties(flight,ticketReturnVo);
        Buyer buyer = buyerMapper.selectById(ticket.getDetailId());
        BeanUtils.copyProperties(flight,ticketReturnVo);
        LambdaQueryWrapper<TicketReturn> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(TicketReturn::getTicketId,ticket.getId());
        TicketReturn ticketReturn = ticketReturnMapper.selectOne(queryWrapper);
//        时间戳-字符串
        ticketReturnVo.setBeginTime(new DateTime(flight.getBeginTime()).toString("HH:mm"));
        ticketReturnVo.setEndTime(new DateTime(flight.getEndTime()).toString("HH:mm"));
        ticketReturnVo.setTicketId(String.valueOf(ticket.getId()));
        ticketReturnVo.setPrice(ticket.getTicketPrice());
        ticketReturnVo.setBuyerName(buyer.getPassengerName());
        ticketReturnVo.setIsCompute(ticketReturn.getIswatch());
        ticketReturnVo.setResource(ticketReturn.getReason());
//        得到指定的航班 set 对应属性
        return ticketReturnVo;
    }


}
