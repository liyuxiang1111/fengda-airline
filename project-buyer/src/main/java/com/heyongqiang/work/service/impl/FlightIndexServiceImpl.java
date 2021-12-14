package com.heyongqiang.work.service.impl;

import com.heyongqiang.work.dao.mapper.RecommendMapper;
import com.heyongqiang.work.dao.pojo.Flight;
import com.heyongqiang.work.dao.pojo.Recommend;
import com.heyongqiang.work.service.FlightIndexService;
import com.heyongqiang.work.vo.FlightSearchVo;
import com.heyongqiang.work.vo.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class FlightIndexServiceImpl implements FlightIndexService {

    @Resource
    private RecommendMapper recommendMapper;



    @Override
    public Result searchIndexFlight() {
        /**
         * 查询完直接返回
         */
        List<Recommend> recommends = recommendMapper.selectList(null);

        return Result.success(copyList(recommends));
    }


    /**
     * 状态转移
     * @param flights
     * @return
     */

    public List<FlightSearchVo> copyList(List<Recommend> flights){
        List<FlightSearchVo> flightSearchVoList = new ArrayList<>();
        for (Recommend params : flights) {
            flightSearchVoList.add(copy(params));
        }
        return flightSearchVoList;
    }

    public FlightSearchVo copy(Recommend flight){
        FlightSearchVo flightSearchVo = new FlightSearchVo();
        BeanUtils.copyProperties(flight,flightSearchVo);
        /**
         * 根据  飞机的型号 拿到飞机的基本信息
         */
        flightSearchVo.setFlightId(String.valueOf(flight.getId()));
//        起步价
        flightSearchVo.setLastPrice(flight.getEconomyPrice());
        return flightSearchVo;
    }


}
