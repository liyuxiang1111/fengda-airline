package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class Ticket {

    private Long id;

    private Integer flightId;

    private Integer planeId;

    private String ticketPrice;

    private Integer sell;

    /**
     *  0 头等舱
     *  1 商务舱
     *  2 经济舱
     */
    private Integer seat;

    private Integer grade;

    private String ticketTime;

    private String ticketDay;


}
