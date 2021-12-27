package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PageParams;

public interface TicketSearchReturnService {

    /**
     * 查看退票的机票
     * @return
     */

    Result findTicketReturn(PageParams pageParams);
}
