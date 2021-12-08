package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class Passenger {

    private Integer userId;

    private String userAccount;

    private String userPwd;

    private Integer passengerTelephone;

    private Integer certificateTypeid;

    private String passengerName;

}
