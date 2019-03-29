package com.pw.grapefarm.daos;

import com.pw.grapefarm.models.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmailCodeDao extends JpaRepository<EmailCode,Integer> {
    List<EmailCode> findByEmailOrderBySendTimeDesc(String email);
}
