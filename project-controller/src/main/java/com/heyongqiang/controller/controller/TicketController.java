package com.heyongqiang.controller.controller;

import com.heyongqiang.controller.service.TicketService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;


    @GetMapping("search/return")
    public Result ticketReturn(){
        return ticketService.findAllTicketReturn();
    }

    @PostMapping("watch")
    public Result ticketWatch(@RequestParam String ticketId){
        return ticketService.ticketWatched(ticketId);
    }

}
