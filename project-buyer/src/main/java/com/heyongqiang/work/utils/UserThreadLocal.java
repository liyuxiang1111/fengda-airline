package com.heyongqiang.work.utils;


import com.heyongqiang.work.dao.pojo.Passenger;

public class UserThreadLocal {

    //私有的不希望他构造
    private UserThreadLocal(){}

    private static final ThreadLocal<Passenger> LOCAL = new ThreadLocal<>();

    /**
     * user 放入
     * @param sysUser
     */
    public static void put(Passenger sysUser){
        LOCAL.set(sysUser);
    }

    /**
     * user 取出
     * @return
     */
    public static Passenger get(){
        return LOCAL.get();
    }

    /**
     * 移除 user
     */
    public static void remove(){
        LOCAL.remove();
    }
}
