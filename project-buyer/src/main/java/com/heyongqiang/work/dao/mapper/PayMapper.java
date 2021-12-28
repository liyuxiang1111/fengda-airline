package com.heyongqiang.work.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heyongqiang.work.dao.pojo.Pay;

import java.util.List;

public interface PayMapper extends BaseMapper<Pay> {
    List<String> selectTicketIdList(Long id);

    List<Pay> selectPayListByLimit(Long id, int pageNum, int pageSize);
}
