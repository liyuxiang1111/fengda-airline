package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.service.TicketSearchReturnService;
import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.TicketChangeParams;
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
    public Result searchTicket(){
        return ticketSearchNormalService.findTicketByUserId();
    }

    @GetMapping("search/return")
    public Result searchTicketReturn(){
        return ticketSearchReturnService.findTicketReturn();
    }


    @PostMapping
    public Result changeTicketDay(@RequestBody TicketChangeParams ticketChangeParams){
        return ticketService.changeTicketDay(ticketChangeParams);
    }



}
