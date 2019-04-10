package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "farm_name")
    private String farmName;

    @Column(name = "grape_name")
    private String grapeName;

    @Column
    private double longitude;

    @Column
    private double latitude;

    @Column(length = 4000)
    private String modelData;
}
