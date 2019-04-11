package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "farm_name",nullable = false)
    private String farmName;

    @Column(name = "grape_name",nullable = false)
    private String grapeName;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column(length = 4000)
    private String modelData;

    @Column(name = "create_time")
    private Date createTime;
}
