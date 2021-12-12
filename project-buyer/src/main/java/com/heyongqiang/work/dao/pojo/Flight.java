package com.heyongqiang.work.dao.pojo;

import lombok.Data;


@Data
public class Flight {

    private Long id;

    private String flightNum;

    private Long planeId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer economyPrice;

    private Integer businessPrice;

    private Integer firstPrice;

    private String flightDay;

}
