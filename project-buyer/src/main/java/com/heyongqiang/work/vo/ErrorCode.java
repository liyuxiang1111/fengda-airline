package com.heyongqiang.work.vo;


public enum  ErrorCode {

    USER_NOEXIT(10001,"该用户不存在！！"),
    USER_PASSWORD_NO_MATCH(10002,"用户名密码错误!!"),
    NO_PERMISSION(10003,"无访问权限"),
    SESSION_TIME_OUT(10004,"会话超时"),
    NO_LOGIN(10005,"未登录"),
    TOKEN_ERROR(10006,"token为空!!"),
    PARAMS_IS_NULL(10007,"用户名密码不能为空!!!"),
    SQL_UPDATE(10008,"更新数据库失败!!!"),
    MAIN_PARAMS_NULL(10007,"关键的参数为空!!!");


    private int code;
    private String msg;

    ErrorCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
