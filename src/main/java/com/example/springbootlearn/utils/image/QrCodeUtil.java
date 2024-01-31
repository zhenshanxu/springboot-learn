package com.example.springbootlearn.utils.image;

import com.example.springbootlearn.config.SystemObject;
import com.example.springbootlearn.utils.enumType.Common;
import com.example.springbootlearn.utils.enumType.Constant;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @Project springboot-learn
 * @Description 二维码
 * @Author xuzhenshan
 * @Date 2024/1/31 10:07:09
 * @Version 1.0
 */
@Slf4j
public class QrCodeUtil {

    /**
     * 生成二维码
     *
     * @param text     二维码 内容
     * @param width    二维码 宽
     * @param height   二维码 高
     * @param logoFile 二维码图片logo
     */
    public static String generateQrCode(String text, int width, int height, String logoFile) {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 指定编码方式，防止中文乱码
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);// 指定纠错等级
        BitMatrix bitMatrix = null;
        String filePath = null;
        BufferedImage image = null;
        try {
            bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            if (logoFile != null) {
                image = addLogo(bitMatrix, logoFile);
            } else {
                MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig();
                image = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            }
            // 二维码暂存地址
            filePath = SystemObject.uploadConfig.getRootFolder() + File.separator + "QRCode" + File.separator +
                    System.currentTimeMillis();
            Boolean folder = Common.createFolder(filePath);
            if (folder) {
                filePath += File.separator + "_QRCode.png";
                ImageIO.write(image, "png", new File(filePath));
            }
        } catch (WriterException | IOException e) {
            throw new RuntimeException(e);
        }
        return filePath;
    }

    /**
     * 添加二维码logo
     *
     * @param bitMatrix 坐标
     * @param logoFile  logo
     */
    private static BufferedImage addLogo(BitMatrix bitMatrix, String logoFile) throws IOException {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Constant.BLACK : Constant.WHITE);
            }
        }
        Graphics2D graphics = image.createGraphics();
        BufferedImage logoImage = ImageIO.read(new File(logoFile));
        // 计算logo图片大小,可适应长方形图片,根据较短边生成正方形
        int widthValue = image.getWidth() < image.getHeight() ? image.getWidth() / 4 : image.getHeight() / 4;
        // 计算logo图片放置位置
        int x = (image.getWidth() - widthValue) / 2;
        int y = (image.getHeight() - widthValue) / 2;
        // 在二维码图片上绘制logo
        graphics.drawImage(WaterMarkTools.setBorderRadius(logoImage, 30), x, y, widthValue, widthValue, null);
        graphics.setStroke(new BasicStroke(1));
        graphics.setColor(Color.WHITE);
        graphics.drawRect(x, y, widthValue, widthValue); // 矩形边框
        logoImage.flush();
        graphics.dispose();
        return image;
    }
}
