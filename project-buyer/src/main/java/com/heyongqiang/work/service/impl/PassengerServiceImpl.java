package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.work.dao.mapper.PassengerMapper;
import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.service.PassengerService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PassengerServiceImpl implements PassengerService {


    @Resource
    private PassengerMapper passengerMapper;

    /**
     *   首先拿到参数之后判断是否非空
     * @param passengerChangeParams
     * @return
     */
    @Override
    public Result changeUserInformation(PassengerChangeParams passengerChangeParams) {
        String email = passengerChangeParams.getEmail();
        String realname = passengerChangeParams.getRealname();
        Integer gender = passengerChangeParams.getGender();
        String telephoneNumber = passengerChangeParams.getTelephoneNumber();
        String nickname = passengerChangeParams.getNickname();
//        构建lam
        LambdaUpdateWrapper<Passenger> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Passenger::getId, passengerChangeParams.getUserId());
//        new 一个目标对象 将parmas内部的值贴到 对象中
        Passenger passenger = new Passenger();
        if(!StringUtils.isBlank(email)){
            passenger.setEmail(email);
        }
        if(!StringUtils.isBlank(realname)){
            passenger.setRealName(realname);
        }
        if(gender != null){
            passenger.setGender(gender);
        }
        if(!StringUtils.isBlank(telephoneNumber)){
            passenger.setTelephone(telephoneNumber);
        }
        if(!StringUtils.isBlank(nickname)){
            passenger.setNickName(nickname);
        }
        int update = passengerMapper.update(passenger, queryWrapper);
        if(update == 0){
//            更新的条数为 0 条返回一个错判的结果
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
        return Result.success(null);
    }

    /**
     * 修改密码
     * @param passengerPasswordParams
     * @return
     */
    @Override
    public Result changePassengerPwd(PassengerPasswordParams passengerPasswordParams) {
        Passenger passenger = UserThreadLocal.get();
        String userPwd = passengerPasswordParams.getUserPwd();
//        判断参数
        if(userPwd == null){
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
        passenger.setUserPwd(userPwd);
        LambdaUpdateWrapper<Passenger> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Passenger::getId,passenger.getId());
        int update = passengerMapper.update(passenger, updateWrapper);
//        是否更行成功
        if(update == 0){
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
        return Result.success(null);
    }
}
