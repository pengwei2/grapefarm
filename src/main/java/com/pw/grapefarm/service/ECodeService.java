package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

public interface ECodeService {
    @Transactional
    Response sendRegisterCode(String email, String sendType) throws MessagingException;

    @Transactional
    Response sendForgetCode(String email, String sendType);
}
