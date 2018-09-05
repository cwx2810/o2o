package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-05 11:32
 **/
public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop() {
        Shop shop = new Shop();

        // 读取3个对象实例
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        personInfo.setUserId(1L);
        area.setAreaId(2L);
        shopCategory.setShopCategoryId(1L);

        shop.setOwner(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试添加店铺");
        shop.setShopDesc("addTest");
        shop.setShopAddr("addTest");
        shop.setShopImg("addTest");
        shop.setPhone("1234567");
        shop.setPriority(2);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        shop.setCreateTime(new Date());

        File shopImg = new File("C:/images/test.jpg");
        ShopExecution shopExecution = shopService.addShop(shop, shopImg);

        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}
