package com.heyongqiang.controller.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.controller.dao.mapper.TicketReturnMapper;
import com.heyongqiang.controller.dao.pojo.TicketReturn;
import com.heyongqiang.controller.service.TicketService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    private TicketReturnMapper ticketReturnMapper;


    @Override
    public Result findAllTicketReturn() {
        /**
         * 直接查
         */
        List<TicketReturn> ticketReturns = ticketReturnMapper.selectList(null);
        return Result.success(ticketReturns);
    }

    @Override
    public Result ticketWatched(String ticketId) {
        /**
         * 处理指定的饿信息就是update
         */
        LambdaUpdateWrapper<TicketReturn> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(TicketReturn::getIswatch,1);
        updateWrapper.eq(TicketReturn::getId,ticketId);
        ticketReturnMapper.update(null,updateWrapper);

        return Result.success(null);
    }
}

