package com.pw.grapefarm.controller;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.model.Record;
import com.pw.grapefarm.service.RecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(value = "提交记录controller", tags = {"用户提交记录操作接口"})
@RestController
@RequestMapping(value = "/record")
public class RecordController extends BaseController{

    @Autowired
    RecordService recordService;

    @ApiOperation(value = "保存用户提交记录", notes = "访问该接口需要通过认证")
    @PostMapping
    public Response saveRecord(@Valid @RequestBody Record record, BindingResult bindingResult, HttpServletRequest request) {
        record.setUsername(getUserName(request));
        return recordService.saveRecord(record);
    }


    @ApiOperation(value = "获取用户提交记录", notes = "访问该接口需要通过认证")
    @GetMapping
    public Response getRecord(HttpServletRequest request) {
        return  recordService.getRecords(getUserName(request));
    }
}
