package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;

/**
 * @author: LieutenantChen
 * @create: 2018-09-08 23:38
 **/
public interface ProductDao {


    /**
     * 通过productId查询商品
     * @param productId
     * @return
     */
    Product queryProductById(long productId);
    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

    /**
     * 修改商品
     * @param product
     * @return 返回影响的行数
     */
    int updateProduct(Product product);

}
