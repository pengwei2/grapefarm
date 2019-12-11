package com.pw.grapefarm.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "t_vip_goods")
public class VipGoods {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name",nullable = false,unique = true)
    private String name;

    // 购买的类型 1：AUD199/year； 2：AUD89/season
    @Column(name = "type")
    private Integer type;

    //
    @Column(name = "amount")
    private Integer amount;
}
