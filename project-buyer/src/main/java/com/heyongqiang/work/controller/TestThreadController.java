package com.heyongqiang.work.controller;

import com.heyongqiang.work.utils.UserThreadLocal;
import com.heyongqiang.work.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test2221")
@RestController
public class TestThreadController {

    @PostMapping
    public String test(){
        System.out.println(UserThreadLocal.get());
        return "successful";
    }
}
