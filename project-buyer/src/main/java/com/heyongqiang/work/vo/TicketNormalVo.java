package com.heyongqiang.work.vo;


import lombok.Data;

@Data
public class TicketNormalVo {

    private String ticketId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer ticketPrice;

    private String ticketDay;

    private String buyerName;

    private String flightName;
}
