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
                shopCategoryDao.queryShopCategory(null);
        System.out.println(shopCategoryList.size());
    }
}
