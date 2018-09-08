package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
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

    @Test
    public void testBatchInsertProductCategory() {
        // 创建两个商品类别对象
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("商品类别5");
        productCategory.setPriority(1);
        productCategory.setCreateTime(new Date());
        productCategory.setShopId(1L);

        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setProductCategoryName("商品类别6");
        productCategory2.setPriority(2);
        productCategory2.setCreateTime(new Date());
        productCategory2.setShopId(1L);

        // 将两个商品类别对象插入到商品类别列表
        List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
        productCategoryList.add(productCategory);
        productCategoryList.add(productCategory2);

        // 检查返回值
        ProductCategoryExecution productCategoryExecution =
                productCategoryService.batchAddProductCategory(productCategoryList);
        assertEquals(1, productCategoryExecution.getState());
    }
}
