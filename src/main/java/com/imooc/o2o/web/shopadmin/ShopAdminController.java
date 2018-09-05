package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: LieutenantChen
 * @create: 2018-09-05 17:59
 **/
@Controller
@RequestMapping("/shopadmin")
public class ShopAdminController {

    /**
     * 跳转到店铺操作
     * @return
     */
    @RequestMapping(value = "/shopoperation", method = RequestMethod.GET)
    public String shopOperation() {
        // 视图解析器上下文我们已经定义过了，所以前面不用写路径文件夹，后面也不用带.html
        return "shop/shopoperation";
    }
}
