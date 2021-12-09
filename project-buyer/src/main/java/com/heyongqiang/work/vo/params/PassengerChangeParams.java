package com.heyongqiang.work.vo.params;

import lombok.Data;

@Data
public class PassengerChangeParams {

    private String userId;

    private String telephoneNumber;

    private String email;
//昵称
    private String nickname;

    private Integer gender;
//真实名字
    private String realname;

}
