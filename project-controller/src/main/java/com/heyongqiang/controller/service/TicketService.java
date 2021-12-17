package com.heyongqiang.controller.service;

import com.heyongqiang.controller.vo.Result;

public interface TicketService {
    /**
     * 查看所有的机票信息
     * @return
     */

    Result findAllTicketReturn();

    /**
     * 处理指定的 退票信息
     * @return
     */
    Result ticketWatched(String ticketId);

}
