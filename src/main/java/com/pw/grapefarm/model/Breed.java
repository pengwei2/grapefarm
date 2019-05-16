package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_grape_breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "breed_name",nullable = false)
    private String breedName;
}
