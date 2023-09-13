package com.heyongqiang.work.dao.pojo;

import lombok.Data;


@Data
public class Flight {

    private Long id;

    private Long planeId; // 飞机型号ID

    private String beginCity; // 起飞城市

    private String endCity;

    private Long beginTime;

    private Long endTime;

    private String economyPrice;

    private String businessPrice;

    private String firstPrice;

    private String flightName;

}
