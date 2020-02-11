package com.davina.service;

import com.davina.domain.Order;
import com.davina.domain.PageBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IOrderService {
    /**
     * 查询所有订单
     * @return
     */
    List<Order> findAll();

    /**
     * 分页查询
     * @return
     */
    PageInfo<Order> findByPage(int pageNum, int pageSize);

    /**
     * 根据产品id删除订单
     * @param pid
     */
    void deleteByProductId(String[] pid);
}
