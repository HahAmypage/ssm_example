package com.davina.dao;

import com.davina.domain.Order;
import com.davina.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;


import java.util.List;

public interface IOrderDao {
    /**
     * 查询所有订单
     * @return
     */
    @Select("SELECT o.* FROM orders o ")
    @Results({
            /**
             * 封装订单信息
             */
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum",column = "orderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "oderDesc",column = "oderDesc"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderStatus",column = "orderStatus"),

            /**
             * 封装产品信息（property是order的字段名，column是product的字段）
             */
            @Result(property = "product",column = "id",javaType = Product.class,
            one = @One(select = "com.davina.dao.IProductDao.findById",fetchType = FetchType.LAZY))
    })
    List<Order> findAll();

    /**
     * 根据产品id删除订单
     * @param pid
     */
//    @Delete("DELETE FROM orders o WHERE o.productid = #{pid}")
    void deleteByProductId(String[] pid);
}
