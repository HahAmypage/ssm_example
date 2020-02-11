package com.davina.service;

import com.davina.domain.PageBean;
import com.davina.domain.Product;

import java.util.List;

public interface IProductService {

    /**
     * 查询全部
     * @return
     */
    List<Product> findAll();

    /**
     * 添加
     * @param product
     */
    void save(Product product);

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    Product findById(Integer id);

    /**
     * 更新产品
     * @param product
     */
    void update(Product product);

    /**
     * 删除
     * @param productId
     */
    void delete(String productId);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageBean<Product> findByPage(int pageNum, int pageSize);
}
