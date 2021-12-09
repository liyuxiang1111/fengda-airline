package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class Ticket {

    private Integer ticketId;

    private Integer flightId;

    private Integer planeId;

    private String ticketPrice;

    private Integer sell;

    private Integer seat;

    private Integer grade;

    private String ticketTime;

    private String ticketDay;


}
