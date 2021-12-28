package com.heyongqiang.work.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.heyongqiang.work.dao.mapper.CertificateMapper;
import com.heyongqiang.work.dao.mapper.PassengerMapper;
import com.heyongqiang.work.dao.pojo.Certificate;
import com.heyongqiang.work.dao.pojo.Passenger;
import com.heyongqiang.work.service.PassengerService;
import com.heyongqiang.work.utils.JWTUtils;
import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.PassengerVo;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.PassengerChangeParams;
import com.heyongqiang.work.vo.params.PassengerPasswordParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class PassengerServiceImpl implements PassengerService {


    @Resource
    private PassengerMapper passengerMapper;

    @Resource
    private CertificateMapper certificateMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    private final static String slat = "123hyq!@dsfas";


    @Override
    public Passenger checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(map == null){
            return null;
        }
        String userJson = stringRedisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }
//        token 验证通过解析为 token 字符返回
        Passenger passenger = JSON.parseObject(userJson, Passenger.class);
        return passenger;
    }

    /**
     *   首先拿到参数之后判断是否非空
     * @param passengerChangeParams
     * @return
     */
    @Override
    public Result changeUserInformation(PassengerChangeParams passengerChangeParams,String tokens) {
        String email = passengerChangeParams.getEmail();
        String realname = passengerChangeParams.getRealname();
        Integer gender = passengerChangeParams.getGender();
        String telephoneNumber = passengerChangeParams.getTelephoneNumber();
        String nickname = passengerChangeParams.getNickname();
//        构建lam
        LambdaUpdateWrapper<Passenger> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.eq(Passenger::getId, UserThreadLocal.get().getId());
//        new 一个目标对象 将parmas内部的值贴到 对象中
        Passenger passenger = this.checkToken(tokens);
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
//        删除原来的token
        stringRedisTemplate.delete("TOKEN_"+tokens);
        String token = JWTUtils.createToken(passenger.getId());
        stringRedisTemplate.opsForValue().set("TOKEN_" + token , JSON.toJSONString(passenger),1, TimeUnit.DAYS);
        return Result.success(token);
    }


    /**
     * 修改密码
     * @param passengerPasswordParams
     * @return
     */
    @Override
    public Result changePassengerPwd(PassengerPasswordParams passengerPasswordParams,String tokens) {
        Passenger passenger = UserThreadLocal.get();
        String userPwd = DigestUtils.md5Hex(passengerPasswordParams.getUserPwd());

        passenger.setUserPwd(userPwd+slat);
        LambdaUpdateWrapper<Passenger> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Passenger::getId,passenger.getId());
        int update = passengerMapper.update(passenger, updateWrapper);
//        是否更行成功
        if(update == 0){
            return Result.fail(ErrorCode.SQL_UPDATE.getCode(),ErrorCode.SQL_UPDATE.getMsg());
        }
//        删除指定的token  更新token
        String token = JWTUtils.createToken(passenger.getId());
        stringRedisTemplate.delete("TOKEN_"+tokens);
        stringRedisTemplate.opsForValue().set("TOKEN_" + token , JSON.toJSONString(passenger),1, TimeUnit.DAYS);
        return Result.success(null);
    }

    @Override
    public Result getUserInformation(String token) {
        Passenger passenger = checkToken(token);
        if(passenger == null){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
//      铜鼓哦 id 查询证件
        Certificate certificate = certificateMapper.selectById(passenger.getCertificateId());
        PassengerVo passengerVo = new PassengerVo();
        BeanUtils.copyProperties(passenger,passengerVo);
        passengerVo.setCertificate(certificate.getCertificate());
        return Result.success(passengerVo);
    }
}
