package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.FlightMapper;
import com.heyongqiang.work.dao.mapper.PayMapper;
import com.heyongqiang.work.dao.mapper.TicketMapper;
import com.heyongqiang.work.dao.mapper.TicketReturnMapper;
import com.heyongqiang.work.dao.pojo.*;
import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.*;
import com.heyongqiang.work.vo.params.TicketBuyerParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketSearchNormalServiceImpl implements TicketSearchNormalService {

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private PayMapper payMapper;

    @Resource
    private FlightMapper flightMapper;


    /**
     * 查看正常的票
     *  1. 拿到用户id
     *  2. 按照id 查看出所有支付正常的 机票列表
     *  3. 根据机票列表查看出所有的符合条件的 机票信息
     * @return
     */

    @Override
    public Result findTicketByUserId() {
        Passenger passenger = UserThreadLocal.get();
        Long id = passenger.getId();
        /**
         *  select ticket from pay where userid = id and ispay = 1;
         */
        List<String> flightList = payMapper.selectFlightIdList(id);
        /**
         * 根据 id的 list 去 ticket中寻找对应的 list
         */
        List<Ticket> tickets = ticketMapper.findTicketListNormal(flightList);

        return Result.success(copyList(tickets));
    }


    /**
     * 状态转移   ticketListNormal
     * @param tickets
     * @return
     */

    public List<TicketNormalVo> copyList(List<Ticket> tickets){
        List<TicketNormalVo> ticketNormalVoList = new ArrayList<>();
        for (Ticket params : tickets) {
            ticketNormalVoList.add(copy(params));
        }
        return ticketNormalVoList;
    }

    public TicketNormalVo copy(Ticket ticket){
        TicketNormalVo ticketNormalVo = new TicketNormalVo();
//        工具copy
        BeanUtils.copyProperties(ticket,ticketNormalVo);
//        得到指定的航班 set 对应属性
        flightInformations(ticket, ticketNormalVo);
        return ticketNormalVo;
    }



    private void flightInformations(Ticket ticket, TicketNormalVo ticketNormalVo) {
        Long flightId = ticket.getFlightId();
        Flight flight = flightMapper.selectById(flightId);
        ticketNormalVo.setBeginCity(flight.getBeginCity());
        ticketNormalVo.setEndCity(flight.getEndCity());
        ticketNormalVo.setBegingTime(new DateTime(flight.getBeginTime()).toString("yyyy-MM-dd"));
        ticketNormalVo.setEndTime(new DateTime(flight.getEndTime()).toString("yyyy-MM-dd"));
    }

//    public static void main(String[] args) {
//        Long time = 1633414089344L;
//        System.out.println(new DateTime(time).toString("HH:mm"));
//    }


}
