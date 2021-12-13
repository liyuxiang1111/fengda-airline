package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class Recommend {

    private Long id;

    private Long planeId;

    private String beginCity;

    private String endCity;

    private String beginTime;

    private String endTime;

    private Integer economyPrice;

    private Integer businessPrice;

    private Integer firstPrice;

}
