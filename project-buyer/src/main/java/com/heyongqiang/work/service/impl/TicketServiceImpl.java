package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.work.dao.mapper.*;
import com.heyongqiang.work.dao.pojo.*;
import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.TicketBuyerParams;
import com.heyongqiang.work.vo.params.TicketChangeParams;
import com.heyongqiang.work.vo.params.TicketReturnParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.ws.ServiceMode;


@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private FlightMapper flightMapper;

    @Resource
    private TicketMapper ticketMapper;

    @Resource
    private PayMapper payMapper;

    @Resource
    private TicketReturnMapper ticketReturnMapper;

    @Resource
    private BuyerMapper buyerMapper;

    @Override
    public Result ticketBuy(TicketBuyerParams ticketBuyerParams) {
        /**
         * 首先买票拿到顾客的id  然后生成一张票和一个订单
         * 然后返回给用户
         */
        Passenger passenger = UserThreadLocal.get();
        Long flightId = ticketBuyerParams.getFlightId();
//        根据id 查询出来 一个航班基本的信息
        Flight flight = flightMapper.selectById(flightId);
//        生成一个机票对应类
        Ticket ticket = writeTicketByFlight(flight, ticketBuyerParams.getGrade(), ticketBuyerParams.getSeat(), ticketBuyerParams.getDay(), ticketBuyerParams.getPrice());
        this.ticketMapper.insert(ticket);

        Pay pay = new Pay();
        pay.setIspay(0);
        pay.setUserId(passenger.getId());
        pay.setTicketId(ticket.getId());

//        登机个人数据
        Buyer buyer = new Buyer();
        buyer.setCertificate(ticketBuyerParams.getCertificate());
        buyer.setCertificateType(ticketBuyerParams.getCertificateType());
        buyer.setEmail(ticketBuyerParams.getEmail());
        buyer.setPassengerName(ticketBuyerParams.getPassengerName());
        buyer.setTelephone(ticketBuyerParams.getTelephone());
        this.buyerMapper.insert(buyer);

        pay.setBuyerId(buyer.getId());
        this.payMapper.insert(pay);

//        返回生成的订单 id
        return Result.success(pay.getId());
    }



    @Override
    public Result changeTicketDay(TicketChangeParams ticketChangeParams) {
        /**
         * 1. 首先获得参数的数据  tikcetid 和 ticketday
         * 2. 并且拿到本地的redis 中的用户信息
         * 3. 先去查看指定tikcet的userid 是否对的上 如果对不直接返回
         * 4. 如果对上了就修改并且返回成功的消息
         */
        Passenger passenger = UserThreadLocal.get();
        if(passenger == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        Long id = passenger.getId();
        LambdaQueryWrapper<Pay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Pay::getTicketId,ticketChangeParams.getTicketId());
//        通过tikcetid  查找到指定的用户id
        Pay pay = payMapper.selectOne(queryWrapper);
        if(!pay.getUserId().equals(passenger.getId())){
//            不是一个用户 恶意请求 直接返回
            return Result.fail(ErrorCode.NO_PERMISSION.getCode(),ErrorCode.NO_PERMISSION.getMsg());
        }
        LambdaUpdateWrapper<Ticket> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Ticket::getId,ticketChangeParams.getTicketId()).set(Ticket::getFlightDay,ticketChangeParams.getTicketDay());
        int update = ticketMapper.update(null, updateWrapper);
        if(update == 0){
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
        return Result.success(null);
    }

    @Override
    public Result ticketReturn(TicketReturnParams ticketReturnParams) {
        Passenger passenger = UserThreadLocal.get();
        if(passenger == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        /**
         * 退票
         *     首先退票需要向退票表 插入者这一条数据
         *     首先同通过ticketid 查询机票的所有信息
         */
//        根据id 查询到指定机票的全部信息
        Ticket ticket = ticketMapper.selectById(ticketReturnParams.getTicketId());
        ticket.setSell(0);
        this.ticketMapper.updateById(ticket);

        TicketReturn ticketReturn = initTicketReturnInformation(ticket,passenger);
        ticketReturn.setReason(ticketReturnParams.getResource());

        int insert = ticketReturnMapper.insert(ticketReturn);
        if(insert == 0){
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
        return Result.success(null);
    }

    private TicketReturn initTicketReturnInformation(Ticket ticket,Passenger passenger) {
        TicketReturn ticketReturn = new TicketReturn();
        ticketReturn.setUserId(passenger.getId());
        ticketReturn.setFlightId(ticket.getFlightId());
        ticketReturn.setIswatch(false);
        ticketReturn.setPassengerName(passenger.getRealName());
        ticketReturn.setPassengerTelephone(passenger.getTelephone());
        ticketReturn.setTicketId(ticket.getId());
        ticketReturn.setTime(System.currentTimeMillis());
        ticketReturn.setSeat(ticket.getSeat());
        ticketReturn.setTicketPrice(ticket.getTicketPrice());
        return ticketReturn;
    }


    /**
     * 快速构建 机票
     * @param flight
     * @param grade
     * @param seat
     * @param day
     * @param price
     * @return
     */

    private Ticket writeTicketByFlight(Flight flight, Integer grade, Integer seat, String day, Integer price) {
        Ticket ticket = new Ticket();
        ticket.setFlightId(flight.getId());
        ticket.setGrade(grade);
        ticket.setPlaneId(flight.getPlaneId());
        ticket.setSeat(seat);
        ticket.setSell(1);
        ticket.setFlightDay(day);
        ticket.setTicketPrice(price);
        return ticket;
    }
}
