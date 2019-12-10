package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "t_vipuser")
public class VipUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    // vip结束日期
    @Column(name = "end_date")
    private Date endDate;

    // 支付的日期
    @Column(name = "start_date")
    private Date startDate;

    // 购买的类型 1：AUD199/year； 2：AUD89/season
    @Column(name = "type")
    private Integer type;

    // 支付流水号
    @Column(name = "transaction_id")
    private Integer transactionId;
}
