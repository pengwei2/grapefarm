package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.service.ECodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

import static com.pw.grapefarm.common.Response.*;

@RestController
@RequestMapping(value = "/emailcode")
public class EmailCodeController extends BaseController {

    @Autowired
    ECodeService eCodeService;

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
