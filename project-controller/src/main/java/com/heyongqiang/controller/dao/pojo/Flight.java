package com.heyongqiang.controller.dao.pojo;

import lombok.Data;


@Data
public class Flight {

    private Long id;

    private Long planeId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private String economyPrice;

    private String businessPrice;

    private String firstPrice;

    private String flightName;

}
