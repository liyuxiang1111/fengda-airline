package com.heyongqiang.controller.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.controller.dao.mapper.PassengerMapper;
import com.heyongqiang.controller.dao.pojo.Passenger;
import com.heyongqiang.controller.service.PassengerService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class PassengerServiceImpl implements PassengerService {

    @Resource
    private PassengerMapper passengerMapper;

    @Override
    public Result searchAllPassenger() {
        /**
         *  直接查询然后返回
         */
        List<Passenger> passengers = passengerMapper.selectList(null);
        return Result.success(passengers);
    }

    @Override
    public Result changeRole(String passengerId) {
        /**
         *  修改
         */
        LambdaUpdateWrapper<Passenger> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Passenger::getId,Long.parseLong(passengerId));
        updateWrapper.set(Passenger::getRole,1);
        int update = passengerMapper.update(null, updateWrapper);
        if(update == 0){
            return Result.fail(2004,"更新数据库失败");
        }
        return Result.success(null);
    }
}

