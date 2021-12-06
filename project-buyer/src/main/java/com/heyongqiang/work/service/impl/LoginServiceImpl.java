package com.heyongqiang.work.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heyongqiang.work.dao.mapper.LoginMapper;
import com.heyongqiang.work.dao.pojo.User;
import com.heyongqiang.work.service.LoginService;
import com.heyongqiang.work.utils.JWTUtils;
import com.heyongqiang.work.vo.ErrorCode;
import com.heyongqiang.work.vo.Result;
import com.heyongqiang.work.vo.params.LoginParams;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, User> implements LoginService {

    @Resource
    private RedisTemplate<String,String> redisTemplates;

//    加密
    private final static String slat = "123hyq!@dsfas";

    /**
     * 用户登录
     * @param loginParams
     * @return
     */

    @Override
    public Result login(LoginParams loginParams) {
        /**
         *  1. 去数据库查询这个用户
         *  2. 是否有该用户 没有则返回错误码 1000001 有则判断密码
         *  3. 密码相同就使用jwt的工具类生成token存入redis并返回token
         *  4. 如果不对那么就直接返回错误  1000002  用户密码错误
         */
//        判读用户名或者密码是否为空
        String username = loginParams.getUserName();
        String userpwd = loginParams.getUserPwd();
        if(StringUtils.isBlank(username)&&StringUtils.isBlank(userpwd)){
//            返回错误信息和状态码
            return Result.fail(ErrorCode.PARAMS_IS_NULL.getCode(),ErrorCode.PARAMS_IS_NULL.getMsg());
        }
//        去数据库查询是否有这个用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,loginParams.getUserName());
        User sysUser = getOne(queryWrapper);
        if(sysUser == null){
//            没有这个用户
            return Result.fail(ErrorCode.USER_NOEXIT.getCode(),ErrorCode.USER_NOEXIT.getMsg());
        }
//        用户输入的加密
        userpwd = DigestUtils.md5Hex(userpwd+slat);
        if(!userpwd.equals(sysUser.getUserPwd())){
//            用户密码错误
            return Result.fail(ErrorCode.USER_PASSWORD_NO_MATCH.getCode(),ErrorCode.USER_PASSWORD_NO_MATCH.getMsg());
        }
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplates.opsForValue().set("TOKEN_" + token , JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
