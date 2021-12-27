package com.heyongqiang.work.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heyongqiang.work.dao.pojo.Ticket;

import java.util.List;

public interface TicketMapper extends BaseMapper<Ticket> {

    List<Ticket> findTicketListNormal(List<String> ticketList,int pageNum, int pageSize);

    List<Ticket> findTicketListReturn(List<String> ticketList, int pageNum, int pageSize);
}
