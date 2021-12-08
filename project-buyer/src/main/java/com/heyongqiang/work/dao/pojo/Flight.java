package com.heyongqiang.work.dao.pojo;

import lombok.Data;


@Data
public class Flight {

    private Long flightId;

    private String flightNum;

    private Integer planeId;

    private String beginCity;

    private String endCity;

    private Long beginTime;

    private Long endTime;

    private Integer economyPrice;

    private Integer businessPrice;

    private Integer firstPrice;

}
