package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;

public interface PassengerService {
    /**
     * 修改 用户的信息
     * @param passengerChangeParams
     */
    Result changeUserInformation(PassengerChangeParams passengerChangeParams);

    /**
     * 修改密码
     * @param passengerPasswordParams
     * @return
     */
    Result changePassengerPwd(PassengerPasswordParams passengerPasswordParams);
}
