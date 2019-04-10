package com.pw.grapefarm.controller;

import com.pw.grapefarm.security.JwtTokenUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    protected String getUserName(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header,"Bearer ");

        return JwtTokenUtils.getUsername(token);
    }
}
