package com.heyongqiang.controller.service.impl;

import com.heyongqiang.controller.dao.mapper.PlaneMapper;
import com.heyongqiang.controller.dao.pojo.Plane;
import com.heyongqiang.controller.service.PlaneService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PlaneServiceImpl implements PlaneService {

    @Resource
    private PlaneMapper planeMapper;

    @Override
    public Result findAllPlane() {
        /**
         * 查看所有的机票 直接查
         */
        List<Plane> planes = planeMapper.selectList(null);
        return Result.success(planes);
    }
}
