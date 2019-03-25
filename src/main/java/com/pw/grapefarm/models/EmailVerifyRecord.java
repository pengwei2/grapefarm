package com.pw.grapefarm.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_email_verify_record")
public class EmailVerifyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String email;

    @Column(name = "send_type",nullable = false)
    private String sendType;

    @Column(name = "send_time",nullable = false)
    private Date sendTime;
}
