package com.heyongqiang.work.dao.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class FlightDiscount {

    private Integer id;

    private String discount;

    private Timestamp discountTime;

}
