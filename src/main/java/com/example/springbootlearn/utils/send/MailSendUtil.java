package com.example.springbootlearn.utils.send;

import com.example.springbootlearn.config.SystemObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @Project springboot-learn
 * @Description
 * @Author Attachment
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

    /**
     * 使用 MimeMessagePreparator  回掉接口來发送html 同时 也可以使用 MimeMessageHelper
     *
     * @param toMail  收件人地址
     * @param subject 标题
     * @param content html内容
     */
    public void sendWithHtml(String toMail, String subject, String content) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
                mimeMessage.setSubject(subject);
                mimeMessage.setFrom(new InternetAddress(SystemObject.mailConfig.getUserName()));
                mimeMessage.setText(content, StandardCharsets.UTF_8.name(), "html");
            }
        };
        this.mailSender.send(preparator);
    }

    /**
     * 发送带有附件的邮件
     *
     * @param toMail  收件人地址
     * @param subject 主题（标题）
     * @param content 内容
     * @param files   附件
     */
    public void sendWithAttachment(String toMail, String subject, String content, List<Map<String, String>> files) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toMail);
            helper.setSubject(subject);
            //支持发送html setText(String text, boolean html)
            helper.setText(content);
            helper.setFrom(SystemObject.mailConfig.getUserName());
            for (Map<String, String> file : files) {
                Resource resource = resourceLoader.getResource(file.get("url"));
                helper.addAttachment(file.get("fileName"), resource);
            }
            this.mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("邮件附件发送异常：" + e.getMessage());
        }
    }
}
