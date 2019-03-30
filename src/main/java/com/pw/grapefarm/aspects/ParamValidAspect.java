package com.pw.grapefarm.aspects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pw.grapefarm.annotations.ParamValid;
import com.pw.grapefarm.commons.Response;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Log4j2
@Component
public class ParamValidAspect {

    // 常规错误码
    private static final int COMMON_ERROR_CODE = 2;

    @Autowired
    ObjectMapper objectMapper;

    @Before("@annotation(paramValid)")
    public void paramValid(JoinPoint point, ParamValid paramValid) {
        Object[] paramObj = point.getArgs();

        if (paramObj.length > 0) {
            if (paramObj[1] instanceof BindingResult) {
                BindingResult result = (BindingResult) paramObj[1];
                Response<Map> errorMap = this.validRequestParams(result);

                if (errorMap != null) {
                    ServletRequestAttributes res = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                    HttpServletResponse response = res.getResponse();
                    response.setCharacterEncoding("UTF-8");
                    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    response.setStatus(HttpStatus.OK.value());

                    OutputStream output = null;
                    try {
                        output = response.getOutputStream();
                        String error = objectMapper.writeValueAsString(errorMap);
                        log.info("aop 检测到参数不规范" + error);
                        output.write(error.getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    } finally {
                        try {
                            if (output != null) {
                                output.close();
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    /**
     * 校验
     */
    private Response<Map> validRequestParams(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> lists = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                lists.add(objectError.getDefaultMessage());
            }

            Map<String,List> map = new HashMap<>();
            map.put("list",lists);
            return new Response<>(COMMON_ERROR_CODE, "something is wrong", map);
        }
        return null;
    }


}
