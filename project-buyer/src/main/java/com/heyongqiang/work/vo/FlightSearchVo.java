package com.heyongqiang.work.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

@Data
public class FlightSearchVo {

//    出发地
    private String begincity;

    //    降落时间
    private String endTime;

//目的地
    private String endCity;

//    起飞时间
    private String beginTime;

//  航班名称
    private String planeNum;

//    经济舱座位
    private Boolean iseconomy;

//    商务舱座位
    private Boolean isbusiness;

//    头等舱座位
    private Boolean isfirst;

//    起步价
    private Integer lastPrice;

//  1
    private Integer economyPrice;

//  2
    private Integer businessPrice;

//  0
    private Integer firstPrice;

}
