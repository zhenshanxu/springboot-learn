package com.example.springbootlearn.utils.send;

import com.example.springbootlearn.config.SystemObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author xuzhenshan
 * @Date 2023/12/28 10:35:53
 * @Version 1.0
 */
@Slf4j
@Component
public class MailSendUtil {

    private final JavaMailSender mailSender;

    private final ResourceLoader resourceLoader;

    @Autowired(required = false)
    public MailSendUtil(JavaMailSender mailSender, ResourceLoader resourceLoader) {
        this.mailSender = mailSender;
        this.resourceLoader = resourceLoader;
    }

    /**
     * 发送单一用户
     *
     * @param toMail  收件人地址
     * @param subject 标题
     * @param content 内存
     */
    public void simpleSend(String toMail, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toMail);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setFrom(SystemObject.mailConfig.getUserName());
        this.mailSender.send(mailMessage);
    }

    /**
     * 发送 多个地址，并抄送多人，同时隐身抄送多人
     *
     * @param toMail   收件人地址
     * @param ccMails  抄送人地址
     * @param bccMails 隐形抄送地址 不会出现在地址栏
     * @param subject  标题
     * @param content  内容
     */
    public void simpleSend(String[] toMail, String[] ccMails, String[] bccMails, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toMail);
        mailMessage.setCc(ccMails);
        mailMessage.setBcc(bccMails);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);
        mailMessage.setFrom(SystemObject.mailConfig.getUserName());
        this.mailSender.send(mailMessage);
    }


}
