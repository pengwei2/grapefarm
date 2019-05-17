package com.pw.grapefarm.controller;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.FarmDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;

@Api(value = "农场controller")
@RestController
@RequestMapping(value = "/farm")
public class FarmController {

    @Autowired
    private FarmDao farmDao;

    @ApiOperation(value = "获取农场列表")
    @GetMapping
    public Response getFarms() {
        Map<String,List> map = new HashMap<>();
        map.put("farms",farmDao.findAll());
        return new Response(COMMON_SUCCESS_CODE, "成功", map);
    }
}
