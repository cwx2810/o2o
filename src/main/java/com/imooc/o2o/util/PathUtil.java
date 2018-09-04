package com.imooc.o2o.util;

/**
 * @author: LieutenantChen
 * @create: 2018-09-04 11:46
 **/
public class PathUtil {

    private static String seperator = System.getProperty("file.seperator");
    /**
     * 获得图片在服务器的存储根路径
     * @return
     */
    public static String getImgBasePath() {
        String basePath = "";
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().startsWith("win")) {
            basePath = "C:/images/";
        } else {
            basePath = "/home/images/";
        }
        basePath.replace("/", seperator);
        return basePath;
    }

    /**
     * 获得各个商铺自己的图片路径
     * @return
     */
    public static String getShopImgPath(Long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        imagePath.replace("/", seperator);
        return imagePath;
    }
}
