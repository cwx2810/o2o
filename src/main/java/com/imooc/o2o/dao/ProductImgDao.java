package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;

import java.util.List;

/**
 * @author: LieutenantChen
 * @create: 2018-09-08 23:40
 **/
public interface ProductImgDao {

    /**
     * 批量添加商品图片
     * @param productImgList
     * @return 插入了多少行图片
     */
    int batchInsertProductImg(List<ProductImg> productImgList);
}
