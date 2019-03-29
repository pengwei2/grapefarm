package com.pw.grapefarm.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = "密码不能为空")
    @Column(name = "pass_word",nullable = false)
    private String password;

    @NotBlank(message = "邮箱不能为空")
    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Transient
    @NotBlank(message = "验证码不能为空")
    private String code;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "farm_name")
    private String farmName;
}
