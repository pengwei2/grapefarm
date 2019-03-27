package com.pw.grapefarm.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name",nullable = false)
    private String userName;

    @Column(name = "pass_word",nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "farm_name")
    private String farmName;
}
