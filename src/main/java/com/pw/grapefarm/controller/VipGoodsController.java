package com.pw.grapefarm.controller;


import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.VIPGoodsDao;
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

@Api(value = "VIP商品类型")
@RestController
@RequestMapping(value = "/vipgoods")
public class VipGoodsController {
    @Autowired
    private VIPGoodsDao vipGoodsDao;

    @ApiOperation(value = "获取VIP商品列表")
    @GetMapping
    public Response getGoods() {
        Map<String, List> map = new HashMap<>();
        map.put("goods",vipGoodsDao.findAll());
        return new Response(COMMON_SUCCESS_CODE, "成功", map);
    }
}
