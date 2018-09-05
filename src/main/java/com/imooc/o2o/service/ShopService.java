package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @author: LieutenantChen
 * @create: 2018-09-04 17:56
 **/
public interface ShopService {
    /**
     * 添加店铺
     * @param shop
     * @param shopImgInputStream
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
