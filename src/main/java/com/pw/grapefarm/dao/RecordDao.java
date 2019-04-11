package com.pw.grapefarm.dao;

import com.pw.grapefarm.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RecordDao extends JpaRepository<Record,Integer>, JpaSpecificationExecutor<Record> {
}
