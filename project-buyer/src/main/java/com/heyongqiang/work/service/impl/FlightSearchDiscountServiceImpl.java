package com.heyongqiang.work.service.impl;

import com.heyongqiang.work.dao.mapper.FlightDiscountMapper;
import com.heyongqiang.work.dao.pojo.FlightDiscount;
import com.heyongqiang.work.service.FlightSearchDiscountService;
import com.heyongqiang.work.vo.FlightDiscountVo;
import com.heyongqiang.work.vo.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchDiscountServiceImpl implements FlightSearchDiscountService {


    @Resource
    private FlightDiscountMapper flightDiscountMapper;



    @Override
    public Result findTicketDiscount() {
        /**
         *   拿到打折的机票就是直接查询 打折机票表就可以了
         */
        List<FlightDiscount> flightDiscounts = flightDiscountMapper.selectList(null);
        return Result.success(copyList(flightDiscounts));
    }



    /**
     * 类型转化
     * @param flightDiscounts
     * @return
     */

    private List<FlightDiscountVo> copyList(List<FlightDiscount> flightDiscounts) {
        List<FlightDiscountVo> flightDiscountVos = new ArrayList<>();
        for (FlightDiscount flightDiscount : flightDiscounts) {
            flightDiscountVos.add(copy(flightDiscount));
        }
        return  flightDiscountVos;

    }

    private FlightDiscountVo copy(FlightDiscount flightDiscount) {
        FlightDiscountVo flightDiscountVo = new FlightDiscountVo();
        flightDiscountVo.setFlightId(String.valueOf(flightDiscount.getId()));
        flightDiscountVo.setDiscount(flightDiscount.getDiscount());
        return flightDiscountVo;
    }


}
