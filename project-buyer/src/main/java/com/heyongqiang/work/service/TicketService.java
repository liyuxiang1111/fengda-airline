package com.heyongqiang.work.service;


import com.heyongqiang.work.vo.Result;

public interface TicketService {
    /**
     * 通过 threadlocal 来获得本人的id
     * @return
     */
    Result findTicketByUserId();

}
