package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @author: LieutenantChen
 * @create: 2018-09-07 21:25
 **/
public interface ProductCategoryService {

    /**
     * 查询某个店铺下的所有商品类别列表
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryList(long shopId);
}
