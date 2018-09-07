package com.imooc.o2o.web.shopadmin;

import com.imooc.o2o.dto.Result;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: LieutenantChen
 * @create: 2018-09-07 22:35
 **/
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManageController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取各个商铺中的商品分类列表
     * @param httpServletRequest
     * @return
     */
    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(HttpServletRequest httpServletRequest) {

        // 模拟session
        Shop shop = new Shop();
        shop.setShopId(1L);
        httpServletRequest.getSession().setAttribute("currentShop", shop);

        // 获取自己模拟的塞进去的session
        Shop currentShop = (Shop) httpServletRequest.getSession().getAttribute("currentShop");

        List<ProductCategory> productCategoryList = null;

        // 获取到了用户id，返回查询结果
        if (currentShop != null && currentShop.getShopId() > 0) {
            productCategoryList = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<List<ProductCategory>>(true, productCategoryList);
        } else {
            // 没有获取到，返回错误码及解释
            ProductCategoryStateEnum productCategoryStateEnum = ProductCategoryStateEnum.INNER_ERROR;
            return new Result<List<ProductCategory>>(false,
                    productCategoryStateEnum.getState(),
                    productCategoryStateEnum.getStateInfo());
        }
    }



}
