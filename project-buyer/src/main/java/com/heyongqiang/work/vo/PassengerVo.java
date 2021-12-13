package com.heyongqiang.work.vo;


import com.heyongqiang.work.dao.pojo.Certificate;
import lombok.Data;

@Data
public class PassengerVo {

    private String nickName;

    private String realName;

    private String telephone;

    private String certificate;

    private String email;

    private Integer gender;

    private String userImg;
}
