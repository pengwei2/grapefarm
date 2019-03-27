package com.pw.grapefarm.daos;

import com.pw.grapefarm.models.EmailCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailCodeDao extends JpaRepository<EmailCode,Integer> {
    EmailCode findByEmail(String email);
}
