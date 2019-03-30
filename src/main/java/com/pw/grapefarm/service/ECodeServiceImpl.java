package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.common.StatusCode;
import com.pw.grapefarm.dao.EmailCodeDao;
import com.pw.grapefarm.dao.UserDao;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import java.util.Date;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;
import static com.pw.grapefarm.common.Response.cResponse;

@Service
public class ECodeServiceImpl  implements ECodeService{
    @Autowired
    UserDao userDao;

    @Autowired
    EmailCodeDao emailCodeDao;

    @Autowired
    TemplateEngine templateEngine;

    @Autowired
    MailService mailService;


    @Autowired
    ECodeService eCodeService;

    @Override
    public Response sendRegisterCode(String email, String sendType) throws MessagingException {

        // 如果该邮件对应的用户已存在，则发送获取验证码失败
        if(userDao.findByEmail(email) != null){
            return cResponse(StatusCode.email_registered.getCode(), StatusCode.email_registered.getRemark());
        }

        // TODO 同一个邮件一分钟内只允许发送一次注册用户验证码

        String code = CommonUtil.genRandomStr();

        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setSendType(sendType);
        emailCode.setSendTime(new Date());
        emailCodeDao.save(emailCode);

        Context context = new Context();
        context.setVariable("emailCode", code);
        String emailContent = templateEngine.process("registerTemplate", context);
        mailService.sendHtmlMail(email,"用户注册码",null,emailContent);

        return cResponse(COMMON_SUCCESS_CODE,"成功");
    }

    @Override
    public Response sendForgetCode(String email, String sendType) {
        return  null;
    }
}
