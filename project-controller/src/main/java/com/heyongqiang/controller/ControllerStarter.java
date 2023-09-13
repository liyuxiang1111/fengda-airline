package com.heyongqiang.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;

@SpringBootApplication
@MapperScan("com.heyongqiang.controller.dao.mapper")
public class ControllerStarter {

    public static void main(String[] args) {
        SpringApplication.run(ControllerStarter.class);
    }
}