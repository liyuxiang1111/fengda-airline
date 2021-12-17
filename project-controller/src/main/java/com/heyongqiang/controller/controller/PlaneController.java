package com.heyongqiang.controller.controller;


import com.heyongqiang.controller.service.PlaneService;
import com.heyongqiang.controller.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("plane")
public class PlaneController {


    @Resource
    private PlaneService planeService;

    @GetMapping("search")
    public Result searchAllPlane(){
        return planeService.findAllPlane();
    }

}
