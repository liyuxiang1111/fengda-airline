package com.heyongqiang.work.dao.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class FlightData {

    private Integer flightId;

    private Integer planeId;

    private String beginCity;

    private String endCity;

    private Timestamp beginTime;

    private Timestamp endTime;

    private String economyPrice;

    private String businessPrice;

    private String firstPrice;

}
