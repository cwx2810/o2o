package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-05 20:48
 **/
public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testGetShopCategoryList() {
        List<ShopCategory> shopCategoryList =
                shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals("咖啡奶茶", shopCategoryList.get(1).getShopCategoryName());

        ShopCategory parentShopCategory = new ShopCategory();
        ShopCategory childShopCategory = new ShopCategory();
        parentShopCategory.setShopCategoryId(1L);
        childShopCategory.setParent(parentShopCategory);

        shopCategoryList = shopCategoryDao.queryShopCategory(childShopCategory);
        assertEquals("咖啡", shopCategoryList.get(0).getShopCategoryName());
    }
}
