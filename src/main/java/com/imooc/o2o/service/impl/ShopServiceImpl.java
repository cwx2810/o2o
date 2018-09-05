package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author: LieutenantChen
 * @create: 2018-09-04 17:59
 **/
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    /**
     * 添加店铺
     * @param shop
     * @param shopImgInputStream
     * @return
     */
    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        // 空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            // 给不能改变的店铺信息赋值
            shop.setEnableStatus(0);// 设置状态为未审核
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            // 添加店铺其他信息
            int effectedNumber = shopDao.insertShop(shop);
            if (effectedNumber <= 0) {
                throw new ShopOperationException("插入店铺信息失败");
            } else {
                // 店铺创建成功后，检查传入的图片参数是否存在，存储图片
                if (shopImgInputStream != null) {
                    try {
                        // 有图片，则添加图片
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("添加图片失败：" + e.getMessage());
                    }
                    // 添加图片成功后更新图片地址到shop表
                    effectedNumber = shopDao.updateShop(shop);
                    if (effectedNumber <= 0) {
                        throw new ShopOperationException("图片地址更新失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("添加店铺失败：" + e.getMessage());
        }

        // 返回我们定义的返回集，即返回插入的shop的状态和状态解释
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * 店铺创建成功后插入图片
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @throws IOException
     */
    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) throws IOException {
        String targetAddress = PathUtil.getShopImgPath(shop.getShopId());
        String relativeAddress = ImageUtil.generateThumbnail(shopImgInputStream, fileName, targetAddress);
        shop.setShopImg(relativeAddress);
    }
}
