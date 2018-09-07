package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-04 10:00
 **/
public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    @Test
    public void testQueryShopListAndCount() {
        // 创建店铺和店家对象
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();

        owner.setUserId(1L);
        shopCondition.setOwner(owner);

        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 3);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("店铺列表每页：" + shopList.size());
        System.out.println("店铺总数：" + count);

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);
        shopList = shopDao.queryShopList(shopCondition, 0, 2);
        count = shopDao.queryShopCount(shopCondition);
        System.out.println("1号店铺类别店铺列表每页：" + shopList.size());
        System.out.println("1号店铺类别店铺总数：" + count);
    }

    @Test
    public void testQueryByShopId() {
        long shopId = 1;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

    @Test
    public void testInsertShop() {
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
        shop.setShopName("测试店铺2");
        shop.setShopDesc("test2");
        shop.setShopAddr("test2");
        shop.setShopImg("test2");
        shop.setPhone("1234567");
        shop.setPriority(1);
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        shop.setCreateTime(new Date());

        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("测试更改描述");
        shop.setShopAddr("测试更改地址");
        shop.setLastEditTime(new Date());
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
}
