package com.heyongqiang.work.dao.pojo;


import lombok.Data;

@Data
public class Certificate {

    private Long id;

    private String certificate;

    private String discount; // 总计

    private String certificateType;

}
