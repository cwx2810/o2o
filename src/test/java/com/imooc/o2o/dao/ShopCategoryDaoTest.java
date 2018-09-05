package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-05 19:35
 **/
public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory() {
        // 测试查询分类总数
        List<ShopCategory> shopCategoryList =
                shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(2, shopCategoryList.size());

        // 测试查询子类类别数
        ShopCategory childCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        // 设置父类是咖啡奶茶
        parentCategory.setShopCategoryId(1L);
        // 设置子类的父亲是父类
        childCategory.setParent(parentCategory);
        // 单独查询子类中的类别
        shopCategoryList = shopCategoryDao.queryShopCategory(childCategory);
        assertEquals(1, shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }
}
