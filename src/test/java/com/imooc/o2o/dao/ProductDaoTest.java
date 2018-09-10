package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-08 23:54
 **/
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;

    @Test
    public void testInsertProduct() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2L);

        Product product1 = new Product();
        product1.setProductName("测试商品1");
        product1.setProductDesc("测试描述1");
        product1.setImgAddr("test");
        product1.setPriority(1);
        product1.setEnableStatus(1);
        product1.setCreateTime(new Date());
        product1.setLastEditTime(new Date());
        product1.setShop(shop);
        product1.setProductCategory(productCategory);

        Product product2 = new Product();
        product2.setProductName("测试商品1");
        product2.setProductDesc("测试描述1");
        product2.setImgAddr("test");
        product2.setPriority(2);
        product2.setEnableStatus(0);
        product2.setCreateTime(new Date());
        product2.setLastEditTime(new Date());
        product2.setShop(shop);
        product2.setProductCategory(productCategory);

        Product product3 = new Product();
        product3.setProductName("测试商品1");
        product3.setProductDesc("测试描述1");
        product3.setImgAddr("test");
        product3.setPriority(3);
        product3.setEnableStatus(1);
        product3.setCreateTime(new Date());
        product3.setLastEditTime(new Date());
        product3.setShop(shop);
        product3.setProductCategory(productCategory);

        int effectedNumber = productDao.insertProduct(product1);
        assertEquals(1, effectedNumber);

        effectedNumber = productDao.insertProduct(product2);
        assertEquals(1, effectedNumber);

        effectedNumber = productDao.insertProduct(product3);
        assertEquals(1, effectedNumber);
    }
}
