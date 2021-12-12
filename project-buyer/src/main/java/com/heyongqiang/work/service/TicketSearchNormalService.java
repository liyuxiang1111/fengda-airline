package com.heyongqiang.work.service;


import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.TicketBuyerParams;

public interface TicketSearchNormalService {
    /**
     * 通过 threadlocal 来获得本人的id
     * @return
     */
    Result findTicketByUserId();



}
