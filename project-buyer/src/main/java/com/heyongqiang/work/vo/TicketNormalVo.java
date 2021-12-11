package com.heyongqiang.work.vo;


import lombok.Data;

@Data
public class TicketNormalVo {

    private Long ticketId;

    private String beginCity;

    private String endCity;

    private String begingTime;

    private String endTime;

    private Integer ticketPrice;

    private String ticketDay;
}
