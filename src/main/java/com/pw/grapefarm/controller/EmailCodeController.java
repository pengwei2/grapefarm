package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.common.SendType;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.service.ECodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

import static com.pw.grapefarm.common.Response.*;

@Api(value = "邮箱验证码controller",tags = {"邮箱验证码操作接口"})
@RestController
@RequestMapping(value = "/emailcode")
public class EmailCodeController extends BaseController {

    @Autowired
    ECodeService eCodeService;

    @ApiOperation(value = "验证码发送接口",
            notes = "sendType=register则是发送用户注册邮箱验证码：" +
                    "\n statusCode=1代表返回成功" +
                    "\n statusCode=2代表发生常规错误" +
                    "\n statusCode=3代表该邮箱已经注册" +
                    "\n----------------------------"+
                    "\n sendType=forget则是发送密码重置邮箱验证码：" +
                    "\n statusCode=1代表返回成功" +
                    "\n statusCode=2代表发生常规错误" +
                    "\n statusCode=3代表该邮箱对应的用户不存在" +
                    "\n ")
    @ParamValid
    @PostMapping
    public Response sendEmailCode(@Valid @RequestBody EmailCode input, BindingResult bindingResult) throws MessagingException {
        String sendType = input.getSendType();

        if (sendType.equals(SendType.register.name())){
            return eCodeService.sendRegisterCode(input.getEmail(),sendType);
        }

        if (sendType.equals(SendType.forget.name())){
            return eCodeService.sendForgetCode(input.getEmail(),sendType);
        }

        return cResponse(COMMON_ERROR_CODE,"验证码类型错误！");
    }
}
