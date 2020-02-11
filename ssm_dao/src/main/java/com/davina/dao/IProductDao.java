package com.davina.dao;

import com.davina.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductDao {

    /**
     * 查询全部
     * @return
     */
    @Select("SELECT * FROM product")
    List<Product> findAll();

    /**
     * 添加
     * @param product
     */
    @Insert("INSERT INTO product VALUES(SEQ_PRODUCT.Nextval,#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * 根据id查找产品
     * @param id
     * @return
     */
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Integer id);

    /**
     * 更新
     * @param product
     */
    @Update("UPDATE product SET productNum = #{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime},productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus} WHERE id = #{id}")
    void update(Product product);

    /**
     * 删除
     * @param pids
     */
    void delete(String[] pids);

    /**
     * 查询分页结果
     * @param index
     * @param pageSize
     * @return
     */
    @Select("SELECT * FROM (SELECT p.* , rownum rn FROM product p WHERE rownum <= #{index})" +
            " WHERE rn > #{pageSize}")
    List<Product> findByPage(@Param("index") Integer index ,@Param("pageSize") Integer pageSize);

    /**
     * 总记录数
     * @return
     */
    @Select("SELECT count(*) FROM product ")
    Long count();
}
