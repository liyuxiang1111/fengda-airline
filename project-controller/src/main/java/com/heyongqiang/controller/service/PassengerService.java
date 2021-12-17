package com.heyongqiang.controller.service;

import com.heyongqiang.controller.vo.Result;

public interface PassengerService {
    /**
     * 查询所有用户信息
     * @return
     */
    Result searchAllPassenger();

    /**
     * 修改用户身份
     * @return
     */
    Result changeRole(String passengerId);
}
