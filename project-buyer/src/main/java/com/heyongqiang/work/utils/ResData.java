package com.heyongqiang.work.utils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 定义接口返回值结构
 */
public class ResData implements Serializable {

    // 验证token 0 - token失效  1 - token正常
    private int tokenCheck = 1;

    // 接口返回值状态 0 - 失败  1 - 成功
    private int status = 1;

    // 接口返回值信息
    private String information = "";

    // 接口返回值
    private Object data;

    public static final String ERRORMSG = "恭喜你遇到bug!";
    /**
     *  设置成功信息
     * @param information 提示消息
     * @param object 返回数据
     */
    public void setSuccess(String information,Object object){
        this.tokenCheck = 1;
        this.status = 1;
        this.information = information;
        this.data = object;
    }

    /**
     *  设置失败信息
     * @param information 提示消息
     * @param object 返回数据
     */
    public void setError(String information,Object object){
        this.tokenCheck = 1;
        this.status = 0;
        this.information = information;
        this.data = object;
    }

    /**
     *  设置token失效信息
     */
    public void setTokenFalse(){
        this.tokenCheck = 0;
        this.status = 0;
        this.information = "token失效,请重新登陆!";
        this.data = new HashMap<String,String>();
    }

    public int getTokenCheck() {
        return tokenCheck;
    }

    public int getStatus() {
        return status;
    }

    public String getInformation() {
        return information;
    }

    public Object getData() {
        return data;
    }
}
