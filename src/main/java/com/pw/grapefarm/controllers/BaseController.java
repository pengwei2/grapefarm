package com.pw.grapefarm.controllers;

import com.pw.grapefarm.commons.Response;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final int COMMON_SUCCESS_CODE = 1;

    public enum SendType {
        register, forget
    }

    public Response<Map> cResponse(Integer statusCode, String statusDesc){
        return new Response<>(statusCode,statusDesc,new HashMap());
    }


    public enum StatusCode{
        email_registered(3,"该邮箱已经注册！"),

        user_email_exist(3,"该邮箱对应的用户已存在！"),
        user_code_not_exist(4,"该邮箱对应的验证码不存在！"),
        user_code_incorrect(5,"验证码不正确");

        private  int code;
        private String remark;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getRemark() {
            return remark;
        }

        StatusCode(int code, String remark) {
            this.code = code;
            this.remark = remark;
        }
    }

}
