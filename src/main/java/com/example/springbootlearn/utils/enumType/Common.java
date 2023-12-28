package com.example.springbootlearn.utils.enumType;

import cn.hutool.crypto.SecureUtil;
import com.example.springbootlearn.config.SystemObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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
                + "<h3 style='text-indent: 4em;'>${name} 您好!</h3>"
                + "<h3 style='text-indent: 30px;'>您登录的账号 ${name} 所需的 ${item} 的令牌验证码 为：</h3>"
                + "<div>"
                + "<p style='text-indent: 75px;font-size:25px;color:darkblue;line-height:10px'> ${verifyCode} </p>"
                + "<p style='text-indent: 20px;font-size:15px;color:red;line-height:10px'>有效时间 ${time} 分钟有效</p>"
                + "<p style='text-indent: 20px;font-size:15px;color:red;line-height:1px'>为保证账号安全，请勿向任何人提供验证码</p>"
                + "</div>";

    }

    /**
     * 返回md5加密后的密码，根据当前配置的salt
     */
    public static String getPasswordMd5(int userId, String password) {
        return SecureUtil.md5(SystemObject.userDefined.getMd5Salt() + userId + password);
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


}
