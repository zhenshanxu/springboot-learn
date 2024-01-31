package com.example.springbootlearn.utils.image;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @Project springboot-learn
 * @Description 水印工具
 * @Author xuzhenshan
 * @Date 2024/1/31 10:17:07
 * @Version 1.0
 */
public class WaterMarkTools {

    /**
     * 图片切圆角
     *
     * @param srcImage 目标图
     * @param radius 圆角半径
     */
    public static BufferedImage setBorderRadius(BufferedImage srcImage, int radius) {
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, width, height, radius, radius);
        g2d.setComposite(AlphaComposite.SrcIn);
        g2d.drawImage(srcImage, 0, 0, width, height, null);
        g2d.dispose();
        return image;
    }

}
