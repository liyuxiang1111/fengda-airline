package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("ticket")
@RestController
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("search/normal")
    public Result searchTicket(){
        return ticketService.findTicketByUserId();
    }

}
