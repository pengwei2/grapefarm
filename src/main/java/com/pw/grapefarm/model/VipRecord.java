package com.pw.grapefarm.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@Entity
@Table(name = "t_vip_record")
public class VipRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Email
    @Column(nullable = false,unique = true)
    private String email;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payer_id")
    private String payerId;

    @Column(name = "amount")
    private String amount;

    @Column(name = "serial")
    private String serial;

    // 支付流水号
    @Column(name = "transaction_id")
    private Integer transactionId;

    @Column(name = "vip_date")
    private Date vipDate;
}
