package com.heyongqiang.work.controller;


import com.heyongqiang.work.service.PayService;
import com.heyongqiang.work.service.TicketSearchNormalService;
import com.heyongqiang.work.service.TicketService;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PayAgainParams;
import com.heyongqiang.work.vo.params.TicketBuyerParams;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("buyer")
public class BuyerTicketController {

    @Resource
    private TicketService ticketService;

    @Resource
    private PayService payService;

    @PostMapping
    public Result ticketBuy(@RequestBody TicketBuyerParams ticketBuyerParams){
        return ticketService.ticketBuy(ticketBuyerParams);
    }

    @PostMapping("again")
    public Result ticketAgain(@RequestBody PayAgainParams payAgainParams){
        return payService.againThisTicket(payAgainParams);
    }

}
