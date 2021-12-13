package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.TicketBuyerParams;
import com.heyongqiang.work.vo.params.TicketChangeParams;
import com.heyongqiang.work.vo.params.TicketReturnParams;

public interface TicketService {


    /**
     * 买票
     * @param ticketBuyerParams
     * @return
     */

    Result ticketBuy(TicketBuyerParams ticketBuyerParams);


    /**
     * 改签
     * @param ticketChangeParams
     * @return
     */
    Result changeTicketDay(TicketChangeParams ticketChangeParams);

    /**
     * 退票
     * @param ticketReturnParams
     * @return
     */

    Result ticketReturn(TicketReturnParams ticketReturnParams);
}
