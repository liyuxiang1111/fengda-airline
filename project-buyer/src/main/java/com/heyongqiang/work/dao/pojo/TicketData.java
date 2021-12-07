package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class TicketData {

    private Integer ticketId;

    private Integer flightId;

    private Integer planeId;

    private String ticketPrice;

    private Integer sell;

    private String seat;

    private Integer grade;

    private String ticketTime;


}
