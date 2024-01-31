package com.example.springbootlearn.utils.enumType;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Project springboot-learn
 * @Description 加密工具
 * @Author xuzhenshan
 * @Date 2024/1/31 16:17:26
 * @Version 1.0
 */

@Slf4j
@Component
public class Encryption {

    /**
     * 凯撒加密
     *
     * @param original 原文
     * @param offset   偏移值
     * @return 加密文本
     */
    private static String kaiserEncrypt(String original, int offset) {
        // 将字符串转为数组
        char[] chars = original.toCharArray();
        StringBuilder buffer = new StringBuilder();
        // 遍历数据
        for (char aChar : chars) {
            // 获取字符的 ASCII 编码
            int asciiCode = aChar;
            asciiCode += offset;
            char result = (char) asciiCode;
            //拼接数据
            buffer.append(result);
        }
        return buffer.toString();
    }

    /**
     * 凯撒解密
     *
     * @param encryptedData 加密 内容
     * @param offset        偏移值
     * @return 解密文本
     */
    private static String decryptKaiser(String encryptedData, int offset) {
        // 将字符串转为字符数组
        char[] chars = encryptedData.toCharArray();
        StringBuilder buffer = new StringBuilder();
        // 遍历数组
        for (char aChar : chars) {
            // 获取字符的ASCII编码
            int asciiCode = aChar;
            // 偏移数据
            asciiCode -= offset;
            // 将偏移后的数据转为字符
            char result = (char) asciiCode;
            // 拼接数据
            buffer.append(result);
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String str = "123456";
        int offset = 0;
        String passwordMd5 = SecureUtil.md5("test" + 1 + str);;
        System.out.println("Md5加密：" +passwordMd5);
        String encode = kaiserEncrypt(passwordMd5, offset);
        System.out.println("加密后：" + encode);

        String decode = decryptKaiser(encode, offset);
        System.out.println("解密后：" + decode);

    }
}
