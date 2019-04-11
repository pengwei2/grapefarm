package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.Record;

import javax.transaction.Transactional;

public interface RecordService {
    @Transactional
    Response saveRecord(Record record);

    @Transactional
    Response getRecords(Integer page,Integer size,String username);
}
