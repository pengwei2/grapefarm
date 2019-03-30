package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.EmailCodeDao;
import com.pw.grapefarm.dao.UserDao;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.service.MailService;
import com.pw.grapefarm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping(value = "/emailcode")
public class EmailCodeController extends BaseController{

    @Autowired
    UserDao userDao;

    @Autowired
    EmailCodeDao emailCodeDao;
    
    @Autowired
    MailService mailService;

    @Autowired
    TemplateEngine templateEngine;

    @ParamValid
    @PostMapping
    public Response<Map> sendEmailCode(@Valid @RequestBody EmailCode input, BindingResult bindingResult) throws MessagingException {
        String email = input.getEmail();

        // 如果该邮件对应的用户已存在，则发送获取验证码失败
        if(userDao.findByEmail(email) != null){
            return cResponse(StatusCode.email_registered.getCode(),StatusCode.email_registered.getRemark());
        }

        // TODO 同一个邮件一分钟内只允许发送一次注册用户验证码

        String code = CommonUtil.genRandomStr();

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setSendType(SendType.register.name());
        emailCode.setSendTime(new Date());
        emailCodeDao.save(emailCode);

        Context context = new Context();
        context.setVariable("emailCode", code);
        String emailContent = templateEngine.process("registerTemplate", context);
        mailService.sendHtmlMail(email,"用户注册码",null,emailContent);

        return cResponse(COMMON_SUCCESS_CODE,"成功");
    }
}
