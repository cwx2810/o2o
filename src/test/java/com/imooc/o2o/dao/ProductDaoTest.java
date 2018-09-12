package com.imooc.o2o.dao;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author: LieutenantChen
 * @create: 2018-09-08 23:54
 **/
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Test
    public void testQueryProductList() throws Exception {

        Product product = new Product();
        // 返回3条数据
        List<Product> productList = productDao.queryProductList(product, 0, 3);
        assertEquals(3, productList.size());

        // 返回总商品数
        int count = productDao.queryProductCount(product);
        assertEquals(7, count);

        // 模糊查询“测试”
        product.setProductName("测试");
        productList = productDao.queryProductList(product, 0, 3);
        assertEquals(3, productList.size());

        count = productDao.queryProductCount(product);
        assertEquals(3, count);

    }

    @Test
    public void testQueryProductByProductId() {
        // 新建两张图片给productId=1的商品
        ProductImg productImg1 = new ProductImg();
        productImg1.setImgAddr("图片1");
        productImg1.setImgDesc("测试图片");
        productImg1.setCreateTime(new Date());
        productImg1.setPriority(1);
        productImg1.setProductId(1L);

        ProductImg productImg2 = new ProductImg();
        productImg2.setImgDesc("测试图片");
        productImg2.setImgAddr("图片2");
        productImg2.setPriority(2);
        productImg2.setCreateTime(new Date());
        productImg2.setProductId(1L);

        List<ProductImg> productImgList = new ArrayList<ProductImg>();
        productImgList.add(productImg1);
        productImgList.add(productImg2);

        int effectedNumber = productImgDao.batchInsertProductImg(productImgList);
        assertEquals(2, effectedNumber);

        // 查询productId=1的商品信息并校验详情图是否为2个
        Product product = productDao.queryProductById(1L);
        assertEquals(2, product.getProductImgList().size());

        // 删除刚刚添加的详情图
        effectedNumber = productImgDao.deleteProductImgByProductId(1L);
        assertEquals(2, effectedNumber);
    }

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

    @Test
    public void testUpdateProduct() {
        Shop shop = new Shop();
        ProductCategory productCategory = new ProductCategory();
        shop.setShopId(1L);
        productCategory.setProductCategoryId(2L);

        Product product = new Product();
        product.setProductId(1L);
        product.setShop(shop);
        product.setProductCategory(productCategory);

        // 修改1号商铺，2号商品分类的商品名称
        product.setProductName("测试修改商品");
        int effectedNumber = productDao.updateProduct(product);
        assertEquals(1, effectedNumber);
    }

    @Test
    public void testUpdateProductCategoryToNull() {
        // 将商品类别id为4的商品类别下的所有商品的商品类别id置空
        int effectedNumber = productDao.updateProductCategoryToNull(4L);
        assertEquals(1, effectedNumber);
    }
}
