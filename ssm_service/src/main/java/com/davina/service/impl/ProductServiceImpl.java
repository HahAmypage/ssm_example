package com.davina.service.impl;

import com.davina.dao.IProductDao;
import com.davina.domain.PageBean;
import com.davina.domain.Product;
import com.davina.service.IOrderService;
import com.davina.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Autowired
    private IOrderService orderService;
    /**
     * 查询全部
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    /**
     * 添加
     *
     * @param product
     */
    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * 根据id查找产品
     *
     * @param id
     * @return
     */
    @Override
    public Product findById(Integer id) {
        return productDao.findById(id);
    }

    /**
     * 更新产品
     *
     * @param product
     */
    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    /**
     * 删除
     *
     * @param productId
     */
    @Override
    public void delete(String productId) {
        String[] pids = productId.split(",");
        orderService.deleteByProductId(pids); //先删除和产品关联的订单，再删除产品
        productDao.delete(pids);
    }

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Product> findByPage(int pageNum, int pageSize) {

        if (pageNum < 1){
            pageNum = 1;
        }

        List<Product> productList = productDao.findByPage((pageNum * pageSize), (pageNum - 1) * pageSize);
        Long count = productDao.count();

        PageBean<Product> productPageBean = new PageBean<>();
        productPageBean.setPageNum(pageNum);
        productPageBean.setPageSize(pageSize);
        productPageBean.setList(productList);
        productPageBean.setTotalCount(count);

        return productPageBean;
    }
}
