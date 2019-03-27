package com.pw.grapefarm.services;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 发送邮件操作类
 */
@Log4j2
@Component
public class MailServiceImpl implements MailService{

    @Autowired
    JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送文本邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
        } catch (Exception e) {
            log.error("发送简单邮件时发生异常！", e);
        }

    }

    /**
     * 发送html邮件
     * @param to      收件人
     * @param subject 邮件标题
     * @param cc      抄送
     * @param content 邮件内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String[] cc, String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        if(cc != null && cc.length >= 1){
            helper.setCc(cc);
        }
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}