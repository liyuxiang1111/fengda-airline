package com.heyongqiang.work.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.heyongqiang.work.dao.pojo.User;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.LoginParams;

public interface LoginService extends IService<User> {


    Result login(LoginParams loginParams);

}
