package com.heyongqiang.controller.vo.params;

import lombok.Data;

@Data
public class FlightParams {

    private String flightName;

    private String planeId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer economyPrice;

    private Integer businessPrice;

    private Integer firstPrice;


}
