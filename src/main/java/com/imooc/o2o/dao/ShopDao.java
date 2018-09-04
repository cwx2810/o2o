package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

/**
 * @author: LieutenantChen
 * @create: 2018-09-03 23:37
 **/
public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return 1插入成功，-1插入失败
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop
     * @return
     */
    int updateShop(Shop shop);
}
