package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-07 21:34
 **/
public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Test
    public void testQueryProductCategoryList() {
        List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(1L);
        assertEquals("第一个分类", productCategoryList.get(0).getProductCategoryName());
    }
}
