package com.heyongqiang.work.dao.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class PlaneDiscount {

    private Integer flightId;

    private String discount;

    private Timestamp discountTime;

}
