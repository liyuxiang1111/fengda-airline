package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PageParams;
import com.heyongqiang.work.vo.params.PayAgainParams;

public interface PayService {
    /**
     * 同意这一个订单
     * @param payAgainParams
     * @return
     */
    Result againThisTicket(PayAgainParams payAgainParams);

    /**
     * 列出用户的所有订单
     * @return
     */
    Result listPassengerPay(PageParams pageParams);
}
