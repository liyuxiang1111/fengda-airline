package com.heyongqiang.work.service;

import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import com.heyongqiang.work.vo.params.PassengerRegisterParams;

public interface RegisterService {
    Result registerPassenger(PassengerRegisterParams registerParams);
}
