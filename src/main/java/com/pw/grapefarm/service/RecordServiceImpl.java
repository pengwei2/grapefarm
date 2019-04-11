package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.RecordDao;
import com.pw.grapefarm.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;
import static com.pw.grapefarm.common.Response.cResponse;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDao recordDao;

    @Override
    public Response saveRecord(Record record) {
        recordDao.save(record);
        return cResponse(COMMON_SUCCESS_CODE, "成功");
    }

    @Override
    public Response getRecords(String username) {
        return new Response(COMMON_SUCCESS_CODE, "成功",recordDao.getByUsernameOrderByCreateTimeDesc(username));
    }


}
