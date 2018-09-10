package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);

        ShopExecution shopExecution = shopService.getShopList(shopCondition, 2, 3);
        System.out.println("1号店铺分类店铺列表每页数量之第二页：" + shopExecution.getShopList().size());
        System.out.println("1号店铺分类总店铺数" + shopExecution.getCount());

    }

    @Test
    public void testGetByShopId() {
        long shopId = 1;
        Shop shop = shopService.getByShopId(shopId);
        System.out.println("shopId:" + shop.getShopId());
        System.out.println("shopName:" + shop.getShopName());
    }

    @Test
    public void testModifyShop() throws ShopOperationException, FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺");
        File shopImg = new File("C:/images/hetian.jpg");
        InputStream inputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), inputStream);

        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
        System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
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
        shop.setShopName("测试添加店铺再次");
        shop.setShopDesc("addTest111");
        shop.setShopAddr("addTest111");
        shop.setShopImg("addTest111");
        shop.setPhone("1234567");
        shop.setPriority(3);
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        shop.setCreateTime(new Date());

        File shopImg = new File("C:/images/test.jpg");

        InputStream fileInputStream = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), fileInputStream);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);

        assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
    }
}
