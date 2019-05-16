package com.pw.grapefarm.controller;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.BreedDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.pw.grapefarm.common.Response.COMMON_SUCCESS_CODE;

@Api(value = "品种controller")
@RestController
@RequestMapping(value = "/breed")
public class BreedController {

    @Autowired
    private BreedDao breedDao;

    @ApiOperation(value = "获取农场列表")
    @GetMapping
    public Response getBreed() {
        return new Response(COMMON_SUCCESS_CODE, "成功", breedDao.findAll());
    }
}
