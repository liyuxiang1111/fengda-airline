package com.heyongqiang.work.vo.params;


import lombok.Data;

@Data
public class TicketBuyerParams {

//    价格
    private Integer price;
//    机舱等级
    private Integer grade;
//   航班id
    private Long flightId;
//  座位
    private Integer seat;
//  起飞日期
    private String day;
//  登记人名字
    private String passengerName;
//  证件
    private String certificate;
//  证件类型
    private String certificateType;
//  电话
    private String telephone;
//  邮箱
    private String email;

}
