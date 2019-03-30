package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_farm")
public class Farm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "farm_name",nullable = false)
    private String farmName;
}
