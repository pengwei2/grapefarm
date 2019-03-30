package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "t_email_code")
public class EmailCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column
    @NotBlank(message = "邮箱不能为空")
    @Email
    private String email;

    /**
     * register 注册账号
     * forget   忘记密码
     */
    @NotBlank(message = "验证码类型不能为空")
    @Column(name = "send_type",nullable = false)
    private String sendType;

    @Column(name = "send_time",nullable = false)
    private Date sendTime;
}
