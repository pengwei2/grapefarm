package com.pw.grapefarm.daos;

import com.pw.grapefarm.models.EmailVerifyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDao extends JpaRepository<EmailVerifyRecord,Integer> {
}
