package com.heyongqiang.work.vo.params;


import lombok.Data;

import javax.annotation.sql.DataSourceDefinitions;

@Data
public class FlightSearchParams {

    private String beginCity;

    private String endCity;

    private String day;

}
