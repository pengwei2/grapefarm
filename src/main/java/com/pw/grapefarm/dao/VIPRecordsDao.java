package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.VipRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VIPRecordsDao  extends JpaRepository<VipRecord,Integer> {
    List<VipRecord> findByEmail(String email);
}
