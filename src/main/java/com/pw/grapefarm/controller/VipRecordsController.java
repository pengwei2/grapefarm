package com.pw.grapefarm.controller;


import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.VipRecord;
import com.pw.grapefarm.service.VIPRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@Api(value = "购买vip的记录")
@RestController
@RequestMapping(value = "/viprecord")
public class VipRecordsController extends BaseController {
    @Autowired
    VIPRecordService vipRecordService;

    @ApiOperation(value = "保存用户购买vip记录")
    @PostMapping
    public Response saveRecord(@Valid @RequestBody VipRecord record, BindingResult bindingResult, HttpServletRequest request) {
        record.setEmail(getUserName(request));
        record.setVipDate(new Date());
        return vipRecordService.saveRecord(record);
    }

    @ApiOperation(value = "获取用户VIP购买记录")
    @GetMapping
    public Response getRecords(HttpServletRequest request) {
        return vipRecordService.getRecords(getUserName(request));
    }

}
