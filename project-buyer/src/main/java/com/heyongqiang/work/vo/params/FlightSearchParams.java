package com.heyongqiang.work.vo.params;


import lombok.Data;


@Data
public class FlightSearchParams {

//    出发城市
    private String beginCity;
//    到达城市
    private String endCity;
//    出发日期
    private Integer day;
//    每一个数量
    private int pageSize;
//    当前页
    private int pageNum;
}
