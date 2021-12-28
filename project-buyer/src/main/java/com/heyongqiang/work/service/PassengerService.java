package com.heyongqiang.work.service;

import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;

public interface PassengerService {

    /**
     * 检查token是否合法
     * @param token
     * @return
     */
    Passenger checkToken(String token);

    /**
     * 修改 用户的信息
     * @param passengerChangeParams
     */
    Result changeUserInformation(PassengerChangeParams passengerChangeParams,String token);

    /**
     * 修改密码
     * @param passengerPasswordParams
     * @return
     */
    Result changePassengerPwd(PassengerPasswordParams passengerPasswordParams);

    /**
     * 通过token 获得到用户的信息
     * @param token
     * @return
     */
    Result getUserInformation(String token);
}
