package com.heyongqiang.work.vo;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

@Data
public class FlightSearchVo {

//    出发地
    private String begincity;

//目的地
    private String endCity;

//    起飞时间
    private String beginTime;

//    降落时间
    private String endTime;

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

}
