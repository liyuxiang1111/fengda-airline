package com.heyongqiang.work.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.work.dao.mapper.PayMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.dao.pojo.Pay;
import com.heyongqiang.work.dao.pojo.Plane;
import com.heyongqiang.work.service.PayService;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.FlightSearchVo;
import com.heyongqiang.work.vo.PayListVo;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PayAgainParams;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class PayServiceImpl implements PayService {


    @Resource
    private PayMapper payMapper;

    @Override
    public Result againThisTicket(PayAgainParams payAgainParams) {
        Passenger passenger = UserThreadLocal.get();
        if(passenger == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        LambdaUpdateWrapper<Pay> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(Pay::getIspay,1).eq(Pay::getId,payAgainParams.getPayId());
        int update = payMapper.update(null, updateWrapper);
        if(update == 0){
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
        return Result.success(null);
    }

    @Override
    public Result listPassengerPay() {
        /**
         * 首先 拿到所有的订单先需要登录 所以先判断是否登录 然后返回对应的list 就可以了
         */
        Passenger passenger = UserThreadLocal.get();
        if(passenger == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        LambdaQueryWrapper<Pay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Pay::getUserId,passenger.getId());
        List<Pay> pays = payMapper.selectList(queryWrapper);
        return Result.success(copyList(pays));
    }


    /**
     * 状态转移
     * @param pay
     * @return
     */

    public List<PayListVo> copyList(List<Pay> pay){
        List<PayListVo> payListVoList = new ArrayList<>();
        for (Pay params : pay) {
            payListVoList.add(copy(params));
        }
        return payListVoList;
    }

    public PayListVo copy(Pay pay){
        PayListVo payListVo = new PayListVo();
        LambdaQueryWrapper<Flight> queryWrapper = new LambdaQueryWrapper<>();
        BeanUtils.copyProperties(pay,payListVo);
        payListVo.setPayId(pay.getId());
        return payListVo;
    }


}
