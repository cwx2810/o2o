package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-10 15:13
 **/
public class ProductServiceTest extends BaseTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testGetShopList() {
        Product productCondition = new Product();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(2L);
        productCondition.setProductCategory(productCategory);

        ProductExecution productExecution = productService.getProductList(productCondition, 1, 3);
        System.out.println("2号分类店铺列表每页数量之第一页：" + productExecution.getProductList().size());
        System.out.println("2号分类总店铺数" + productExecution.getCount());

    }

    @Test
    public void testAddProduct() throws ShopOperationException, IOException {
        Shop shop = new Shop();
        ProductCategory productCategory = new ProductCategory();

        shop.setShopId(1L);
        productCategory.setProductCategoryId(2L);

        Product product = new Product();
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("我的商品");
        product.setProductDesc("我的商品描述");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        // 创建缩略图
        File file = new File("C:/images/git.jpg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder thumbnail = new ImageHolder(file.getName(), inputStream);

        // 创建两张详情图
        File productImg1 = new File("C:/images/hetian.jpg");
        InputStream inputStream1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:/images/test.jpg");
        InputStream inputStream2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), inputStream1));
        productImgList.add(new ImageHolder(productImg2.getName(), inputStream2));
        //添加商品
        ProductExecution productExecution = productService.addProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }

    @Test
    public void testUpdateProduct() throws ShopOperationException, IOException {
        Shop shop = new Shop();
        ProductCategory productCategory = new ProductCategory();

        shop.setShopId(1L);
        productCategory.setProductCategoryId(2L);

        Product product = new Product();
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);
        product.setProductName("正式的商品");
        product.setProductDesc("正式的商品描述");
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());

        // 创建缩略图
        File file = new File("C:/images/git.jpg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder thumbnail = new ImageHolder(file.getName(), inputStream);

        // 创建两张详情图
        File productImg1 = new File("C:/images/hetian.jpg");
        InputStream inputStream1 = new FileInputStream(productImg1);
        File productImg2 = new File("C:/images/test.jpg");
        InputStream inputStream2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
        productImgList.add(new ImageHolder(productImg1.getName(), inputStream1));
        productImgList.add(new ImageHolder(productImg2.getName(), inputStream2));
        //添加商品
        ProductExecution productExecution = productService.modifyProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), productExecution.getState());
    }

}
