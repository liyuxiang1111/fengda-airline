package com.heyongqiang.work.dao.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TicketReturn {

    private Integer flightId;

    private Integer userId;

    private Integer ticketId;

    private String planeModel;

    private String reason;

    private String passengerName;

    private Integer passengerTelephone;

    private String ticketPrice;

    private Timestamp time;


}
