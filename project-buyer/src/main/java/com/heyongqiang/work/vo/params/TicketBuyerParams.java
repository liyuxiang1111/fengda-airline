package com.heyongqiang.work.vo.params;


import lombok.Data;

@Data
public class TicketBuyerParams {

    private Integer price;

    private Integer grade;

    private Long flightId;

    private Integer seat;

    private String day;

    private String passengerName;

    private String certificate;

    private String certificateType;

    private String telephone;

    private String email;

}
