package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: LieutenantChen
 * @create: 2018-09-07 21:32
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 根据店铺Id获得该店铺下商品列表
     * @param shopId
     * @return
     */
    @Override
    public List<ProductCategory> getProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }
}
