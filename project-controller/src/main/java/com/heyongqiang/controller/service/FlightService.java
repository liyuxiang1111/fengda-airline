package com.heyongqiang.controller.service;


import com.heyongqiang.controller.dao.pojo.Flight;
import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.FlightDiscountParams;
import com.heyongqiang.controller.vo.params.FlightParams;
import com.heyongqiang.controller.vo.params.PageParams;

public interface FlightService {

    /**
     * 添加航班
     * @param flightParams
     * @return
     */
    Result addFlight(FlightParams flightParams);

    /**
     * 设置折扣航班
     * @param flightDiscountParams
     * @return
     */
    Result setDiscountFlight(FlightDiscountParams flightDiscountParams);

    /**
     * 修改指定航班的信息
     * @param flight
     * @return
     */
    Result changeInformation(Flight flight);

    /**
     * 查看所有航班
     * @return
     */
    Result findAllFlight(PageParams pageParams);
}
