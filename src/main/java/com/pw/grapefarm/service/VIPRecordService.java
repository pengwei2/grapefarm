package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.VipRecord;

import javax.transaction.Transactional;

public interface VIPRecordService {

    @Transactional
    Response saveRecord(VipRecord record);

    @Transactional
    Response getRecords(String username);
}
