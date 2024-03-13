package com.example.springbootlearn.utils.enumType;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 14:25:19
 * @Version 1.0
 */

@Slf4j
@Component
public class Common {


    /**
     * 获取一个6位整数
     *
     * @return
     */
    public static int getRandomCode() {
        return (int) ((Math.random() * 900000) + 100000);
    }

    /**
     * 生成邮箱验证码
     *
     * @return
     */
    public static String getVerifyCodeHtml() {
        return "<div style='margin:auto;width:90%'>"
                + "<h2 style='text-align:left'>亲爱的用户：</h2>"
                + "<h3 style='text-indent: 2em;'>${name} 您好!</h3>"
                + "<h3 style='text-indent: 30px;'>您登录的账号 ${name} 所需的 ${item} 的令牌验证码 为：</h3>"
                + "<p style='text-indent: 75px;font-size:25px;color:darkblue;line-height:10px'> ${verifyCode} </p>"
                + "<p style='text-indent: 20px;font-size:15px;color:red;line-height:10px'>有效时间 ${time} 分钟有效</p>"
                + "<p style='text-indent: 20px;font-size:15px;color:red;line-height:1px'>为保证账号安全，请勿向任何人提供验证码</p>"
                + "</div>";

    }

    /**
     * 返回md5加密后的密码，根据当前配置的salt
     */
    public static String getPasswordMd5(int userId, String password, String salt) {
        return SecureUtil.md5(salt + userId + password);
    }

    /**
     * 创建文件夹
     *
     * @param filePath
     * @return
     */
    public static Boolean createFolder(String filePath) {
        boolean mkdirs = Boolean.FALSE;
        File folder = new File(filePath);
        if (!folder.exists()) {
            mkdirs = folder.mkdirs();
        }
        return mkdirs;
    }

    /**
     * 删除文件
     *
     * @param filePath
     * @return
     */
    public static Boolean deleteFile(String filePath) {
        Boolean flag = Boolean.FALSE;
        File file = new File(filePath);
        List<File> fileList = new ArrayList<>();
        if (file.isDirectory()) {
            // 收集文件夹及文件内文件
            List<File> files = new LinkedList<>();
            Objects.requireNonNull(collectFile(file, files));
            files.add(file);
            fileList.addAll(files);
        } else {
            // 收集文件地址
            fileList.add(file);
        }
        for (File fileObjet : fileList) {
            boolean delete = fileObjet.delete();
        }
        fileList = null;
        log.info("文件删除成功!");
        return Boolean.TRUE;
    }

    /**
     * 收集文件路径
     *
     * @param file
     * @return
     */
    private static List<File> collectFile(File file, List<File> fileList) {
        File[] files = file.listFiles();
        for (int i = 0; i < Objects.requireNonNull(files).length; i++) {
            File filePath = files[i];
            if (filePath.isDirectory()) {
                collectFile(filePath, fileList);
            }
            fileList.add(filePath);
        }
        return fileList;
    }

    /**
     * 用于生成密钥
     */
    private final static String[] chars = new String[]{"a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    /**
     * 生成短8位随机唯一码：
     * 短8位UUID思想其实借鉴微博短域名的生成方式，但是其重复概率过高，而且每次生成4个，需要随即选取一个。
     * 本算法利用62个可打印字符，通过随机生成32位UUID，由于UUID都为十六进制，所以将UUID分成8组，每4个为一组，然后通过模62操作，
     * 结果作为索引取出字符，这样重复率大大降低。
     * 经测试，在生成一千万个数据也没有出现重复，完全满足大部分需求。
     * <p>
     * !!!注：使用中为了更加严谨，建议数据库进行二次查重操作
     *
     * @return
     */
    public static String getShortRandomCode() {
        StringBuilder shortBuffer = new StringBuilder();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }

    public static String getSalt(int n){
        char[] chars = "abcdefghijklmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ0123456789".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++)
        {
            char aChar = chars[new Random().nextInt(chars.length)];
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * 数组去重
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int p = 0;
        int q = 1;
        while (q < nums.length) {
            if (nums[p] != nums[q]) {
                if (q - p > 1) {
                    nums[p + 1] = nums[q];
                }
                p++;
            }
            q++;
        }
        System.out.println(Arrays.toString(nums));
        return p + 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            String shortRandomCode = getShortRandomCode();
            System.out.println(shortRandomCode);
        }
    }
}
