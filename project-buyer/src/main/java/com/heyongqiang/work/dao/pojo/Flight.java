package com.heyongqiang.work.dao.pojo;

import lombok.Data;


@Data
public class Flight {

    private Long id;

    private Long planeId;

    private String beginCity;

    private String endCity;

    private Long beginTime;

    private Long endTime;

    private String economyPrice;

    private String businessPrice;

    private String firstPrice;

    private String flightName;

}
