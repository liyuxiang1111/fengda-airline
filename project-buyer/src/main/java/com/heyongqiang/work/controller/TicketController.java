package com.heyongqiang.work.controller;


import com.heyongqiang.work.common.cache.Cache;
import com.heyongqiang.work.service.FlightSearchDiscountService;
import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.service.TicketSearchReturnService;
import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.TicketReturnVo;
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
    @Cache(expire = 60*60*12 ,name = "ticket_normal")
    public Result searchTicket(){
        return ticketSearchNormalService.findTicketByUserId();
    }

    @GetMapping("search/return")
    @Cache(expire = 60*60*12 ,name = "ticket_return")
    public Result searchTicketReturn(){
        return ticketSearchReturnService.findTicketReturn();
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
