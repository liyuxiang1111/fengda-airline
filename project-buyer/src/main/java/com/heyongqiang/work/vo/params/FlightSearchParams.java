package com.heyongqiang.work.vo.params;


import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

@Data
public class FlightSearchParams {

    private String beginCity;

    private String endCity;

    private Integer day;

    private int pageSize;

//    当前页
    private int pageNum;

}
