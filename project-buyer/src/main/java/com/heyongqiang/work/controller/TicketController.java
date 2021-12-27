package com.heyongqiang.work.controller;


import com.heyongqiang.work.common.cache.Cache;
import com.heyongqiang.work.service.FlightSearchDiscountService;
import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.service.TicketSearchReturnService;
import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.TicketReturnVo;
import com.heyongqiang.work.vo.params.PageParams;
import com.heyongqiang.work.vo.params.TicketChangeParams;
import com.heyongqiang.work.vo.params.TicketReturnParams;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("ticket")
@RestController
public class TicketController {

    @Resource
    private TicketSearchNormalService ticketSearchNormalService;

    @Resource
    private TicketSearchReturnService ticketSearchReturnService;


    @Resource
    private TicketService ticketService;



    @GetMapping("search/normal")
    public Result searchTicket(@RequestBody PageParams pageParams){
        return ticketSearchNormalService.findTicketByUserId(pageParams);
    }

    @GetMapping("search/return")
    public Result searchTicketReturn(@RequestBody PageParams pageParams){
        return ticketSearchReturnService.findTicketReturn(pageParams);
    }

    @PostMapping("return")
    public Result ticketReturn(@RequestBody TicketReturnParams ticketReturnParams){
        return ticketService.ticketReturn(ticketReturnParams);
    }

    @PostMapping("modify")
    public Result changeTicketDay(@RequestBody TicketChangeParams ticketChangeParams){
        return ticketService.changeTicketDay(ticketChangeParams);
    }



}
