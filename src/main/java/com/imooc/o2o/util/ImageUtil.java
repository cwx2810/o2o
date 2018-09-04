package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author: LieutenantChen
 * @create: 2018-09-04 11:38
 **/
public class ImageUtil {

    // 由当前线程中运行的类获取resources的路径
    private static String bathPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    // 随机文件名拼接常量
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random RANDOM = new Random();

    /**
     * 生成缩略图
     * @param thumbnail spring传入的图片文件
     * @param targetAddress 图片保存地址，是服务器图片目录之后的地址，
     *                      也就是设置好服务器图片目录，我们这个是此目录下的相对路径
     * @return
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddress) throws IOException {
        String fileName = getRandomFileName();
        String extensionName = getExtensionName(thumbnail);
        makeDirPath(targetAddress);
        // 图片文件最终存储地址
        File dest = new File(PathUtil.getImgBasePath() + targetAddress + fileName + extensionName);

        Thumbnails.of(thumbnail.getInputStream())
                .size(200, 200)
                .outputQuality(0.8f)
                .toFile(dest);
        return targetAddress + fileName + extensionName;
    }

    /**
     * 产生随机文件名，由日期+5个随机数组成
     * @return
     */
    private static String getRandomFileName() {
        String nowTimeStr = SIMPLE_DATE_FORMAT.format(new Date());
        int randomNum = RANDOM.nextInt(89999) + 10000;
        return nowTimeStr + randomNum;
    }

    /**
     * 获得图片扩展名
     * @return
     */
    private static String getExtensionName(CommonsMultipartFile thumbnail) {
        String originalFileName = thumbnail.getOriginalFilename();
        // 获取点号之后的字符
        return originalFileName.substring(originalFileName.indexOf("."));
    }

    /**
     * 如果文件夹不存在，循环创建文件夹
     * @param targetAddress
     */
    private static void makeDirPath(String targetAddress) {
        String filePath = PathUtil.getImgBasePath() + targetAddress;
        File dirPath = new File(filePath);
        if(!dirPath.exists()) {
            dirPath.mkdirs();
        }
    }


}
