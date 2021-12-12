package com.heyongqiang.work.vo;

import lombok.Data;

@Data
public class TicketReturnVo {

    private Long ticketId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer price;

    private Boolean isCompute;


}
