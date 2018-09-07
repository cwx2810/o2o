package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @author: LieutenantChen
 * @create: 2018-09-07 21:07
 **/
public interface ProductCategoryDao {
    /**
     * 通过shopId 查询店铺的商品类别列表
     * @param shopId
     * @return
     */
    List<ProductCategory> queryProductCategoryList(long shopId);
}