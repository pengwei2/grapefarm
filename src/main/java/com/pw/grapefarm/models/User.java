package com.pw.grapefarm.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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

    @Column(nullable = false)
    private String passwd;

    @Column(nullable = false)
    private String email;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
