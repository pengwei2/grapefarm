package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.VIPRecordsDao;
import com.pw.grapefarm.model.VipRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;
import static com.pw.grapefarm.common.Response.cResponse;

@Service
public class VIPRecordServiceImpl implements VIPRecordService {

    @Autowired
    VIPRecordsDao vipRecordsDao;

    @Override
    public Response saveRecord(VipRecord record) {
        vipRecordsDao.save(record);
        return cResponse(COMMON_SUCCESS_CODE, "成功");
    }

    @Override
    public Response getRecords(String username) {
        Map<String,List> map = new HashMap<>();
        map.put("records",vipRecordsDao.findByEmail(username));
        return new Response(COMMON_SUCCESS_CODE, "成功", map);
    }
}
