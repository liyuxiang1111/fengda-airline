package com.heyongqiang.work.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heyongqiang.work.dao.pojo.Flight;

import java.util.List;


public interface FlightMapper extends BaseMapper<Flight> {

    List<Flight> selectPlansLimit(String beginCity, String endCity, int pageNum, int pageSize);

}
