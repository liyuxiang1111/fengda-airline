package com.heyongqiang.work.controller;


import com.heyongqiang.work.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("ticket")
@RestController
public class TicketController {

    @GetMapping("search")
    public Result searchTicket(){
        return null;
    }

}
