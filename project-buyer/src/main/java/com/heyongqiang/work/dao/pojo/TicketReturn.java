package com.heyongqiang.work.dao.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketReturn {

    private Long id;

    private Long flightId;

    private Long userId;

    private Long ticketId;

    private Integer seat;

    private String reason;

    private String passengerName;

    private String passengerTelephone;

    private Integer ticketPrice;

    private Long time;

    private Boolean iswatch;


}
