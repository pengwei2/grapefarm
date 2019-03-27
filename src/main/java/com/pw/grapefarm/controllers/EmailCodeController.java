package com.pw.grapefarm.controllers;

import com.pw.grapefarm.commons.Response;
import com.pw.grapefarm.daos.EmailCodeDao;
import com.pw.grapefarm.models.EmailCode;
import com.pw.grapefarm.services.MailService;
import com.pw.grapefarm.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;

@RestController
@RequestMapping(value = "/emailcode")
public class EmailCodeController extends BaseController{

    @Autowired
    EmailCodeDao emailCodeDao;
    
    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;


    @PostMapping
    public Response sendEmailCode(String email) throws MessagingException {
        //TODO 邮箱需要验证

        String code = CommonUtil.genRandomStr();

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(CommonUtil.genRandomStr());
        emailCode.setEmail(email);
        emailCode.setSendType(SendType.register.name());
        emailCode.setSendTime(new Date());
        emailCodeDao.save(emailCode);

        //TODO 发送验证码给指定邮箱
        Context context = new Context();
        context.setVariable("emailCode", code);
        String emailContent = templateEngine.process("registerTemplate", context);
        mailService.sendHtmlMail(email,"用户注册码",null,emailContent);

        return new Response(HttpStatus.OK.value(),"成功");
    }
}
