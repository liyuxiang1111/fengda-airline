package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heyongqiang.work.dao.mapper.*;
import com.heyongqiang.work.dao.pojo.*;
import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.*;
import com.heyongqiang.work.vo.params.PageParams;
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

    @Resource
    private BuyerMapper buyerMapper;

    /**
     * 查看正常的票
     *  1. 拿到用户id
     *  2. 按照id 查看出所有支付正常的 机票列表
     *  3. 根据机票列表查看出所有的符合条件的 机票信息
     * @return
     */

    @Override
    public Result findTicketByUserId(PageParams pageParams) {
        Passenger passenger = UserThreadLocal.get();
        Long id = passenger.getId();
        /**
         *  select ticket from pay where userid = id and ispay = 1 limit pageNum,pageSize;
         */
        List<String> ticketList = payMapper.selectTicketIdList(id);
        /**
         * 根据 id的 list 去 ticket中寻找对应的 list
         */
        if(ticketList.size() == 0){
            return Result.fail(ErrorCode.NO_INFORMATION.getCode(),ErrorCode.NO_INFORMATION.getMsg());
        }
        List<Ticket> tickets = ticketMapper.findTicketListNormal(ticketList,pageParams.getPageNum(),pageParams.getPageSize());

        Page<TicketNormalVo> page = new Page<>(pageParams.getPageNum(),pageParams.getPageSize(),ticketList.size());

        page.setDataList(copyList(tickets));
        return Result.success(page);
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
        ticketNormalVo.setTicketId(String.valueOf(ticket.getId()));
        ticketNormalVo.setTicketDay(ticket.getFlightDay());
        ticketNormalVo.setBeginCity(flight.getBeginCity());
        ticketNormalVo.setEndCity(flight.getEndCity());
        ticketNormalVo.setBeginTime(new DateTime(flight.getBeginTime()).toString("HH:mm"));
        ticketNormalVo.setEndTime(new DateTime(flight.getEndTime()).toString("HH:mm"));
        Buyer buyer = buyerMapper.selectById(ticket.getDetailId());
        ticketNormalVo.setBuyerName(buyer.getPassengerName());
        ticketNormalVo.setFlightName(flight.getFlightName());
    }

//    public static void main(String[] args) {
//        Long time = 1633414089344L;
//        System.out.println(new DateTime(time).toString("HH:mm"));
//    }


}
