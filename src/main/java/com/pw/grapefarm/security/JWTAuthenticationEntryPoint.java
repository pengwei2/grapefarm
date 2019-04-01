package com.pw.grapefarm.security;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.common.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.pw.grapefarm.common.StatusCode.auth_no_right;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpStatus.OK.value());

        //String reason = "统一处理，原因："+authException.getMessage();
        response.getWriter().write(JsonUtil.toJson(Response.cResponse(auth_no_right.getCode(),auth_no_right.getRemark())));
    }
}
