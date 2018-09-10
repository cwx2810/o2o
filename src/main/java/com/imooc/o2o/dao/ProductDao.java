package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;

/**
 * @author: LieutenantChen
 * @create: 2018-09-08 23:38
 **/
public interface ProductDao {

    /**
     * 插入商品
     * @param product
     * @return
     */
    int insertProduct(Product product);

}
