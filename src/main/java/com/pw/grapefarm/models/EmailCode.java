package com.pw.grapefarm.models;

import lombok.Data;

import javax.persistence.*;
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

    @Column(nullable = false)
    private String email;

    /**
     * register 注册账号
     * forget   忘记密码
     */
    @Column(name = "send_type",nullable = false)
    private String sendType;

    @Column(name = "send_time",nullable = false)
    private Date sendTime;
}
