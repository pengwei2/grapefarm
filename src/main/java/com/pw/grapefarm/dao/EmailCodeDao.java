package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailCodeDao extends JpaRepository<EmailCode,Integer> {
    List<EmailCode> findByEmailOrderBySendTimeDesc(String email);
}
