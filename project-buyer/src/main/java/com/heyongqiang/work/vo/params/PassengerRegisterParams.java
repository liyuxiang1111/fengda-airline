package com.heyongqiang.work.vo.params;


import lombok.Data;

@Data
public class PassengerRegisterParams {

    //昵称
    private String nickname;

//    密码
    private String userPwd;

    private String telephone;

    private String email;

    private Integer gender;

    //真实名字
    private String realName;

    private String certificate;

    private String certificateType;

}
