package com.heyongqiang.controller.service;

import com.heyongqiang.controller.vo.Result;
import com.heyongqiang.controller.vo.params.LoginParams;

public interface LoginService {
    /**
     * 管理员登录
     * @param loginParams
     * @return
     */
    Result login(LoginParams loginParams);
}
