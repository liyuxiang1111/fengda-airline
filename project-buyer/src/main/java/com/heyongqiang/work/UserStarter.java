package com.heyongqiang.work;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.heyongqiang.work.dao.mapper")
@SpringBootApplication
public class UserStarter {
    public static void main(String[] args) {
        SpringApplication.run(UserStarter.class);
    }
}
