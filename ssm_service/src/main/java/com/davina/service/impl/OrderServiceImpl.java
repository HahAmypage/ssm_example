package com.davina.service.impl;

import com.davina.dao.IOrderDao;
import com.davina.domain.Order;
import com.davina.domain.PageBean;
import com.davina.service.IOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    /**
     * 查询所有订单
     *
     * @return
     */
    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public PageInfo<Order> findByPage(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderDao.findAll();
        PageInfo pageInfo = new PageInfo(orderList);
        return pageInfo;
    }

    /**
     * 根据产品id删除订单
     *
     * @param pid
     */
    @Override
    public void deleteByProductId(String[] pid) {
        orderDao.deleteByProductId(pid);
    }
}
