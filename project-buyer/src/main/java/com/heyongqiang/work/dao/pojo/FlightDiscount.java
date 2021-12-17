package com.heyongqiang.work.dao.pojo;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class FlightDiscount {

    private Long id;

    private Integer discount;

    private Long discountTime;

    private Long flightId;

}
