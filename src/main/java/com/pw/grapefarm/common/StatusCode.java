package com.pw.grapefarm.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    // email_code
    email_registered(3, "该邮箱已经注册！"),
    email_forget_not_exist(3,"该邮箱对应的用户不存在！"),

    // user_register
    user_email_exist(3, "该邮箱对应的用户已存在！"),
    user_code_not_exist(4, "该邮箱对应的验证码不存在！"),
    user_code_incorrect(5, "验证码不正确！"),

    // user_forget
    user_forget_email_not_exist(3, "该邮箱对应的用户不存在！"),
    user_forget_code_not_exist(4, "该邮箱对应的验证码不存在！"),
    user_forget_code_incorrect(5, "验证码不正确！");

    private int code;
    private String remark;
}
