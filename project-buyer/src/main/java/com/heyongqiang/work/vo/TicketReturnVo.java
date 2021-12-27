package com.heyongqiang.work.vo;

import lombok.Data;

@Data
public class TicketReturnVo {

    private String ticketId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer price;

    private Boolean isCompute;

//    购买者名字
    private String buyerName;

//    航班名
    private String flightName;

//    退票理由
    private String resource;

}

