package com.pw.grapefarm.controller;


import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.VipUser;
import com.pw.grapefarm.service.VIPService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@Api(value = "VIP用户",tags = {"VIP用户接口"})
@RestController
@RequestMapping("/vipuser")
public class VipUserController extends BaseController  {
    @Autowired
    VIPService vipService;

    @ApiOperation(value = "获取用户的VIP信息")
    @GetMapping
    public Response getVIPInfo(HttpServletRequest request, @RequestParam(value = "email") String email) {
        return vipService.getVIPUserInfo(email);
    }

    @ApiOperation(value = "保存VIP用户")
    @PostMapping
    public Response saveVIPUser(@Valid @RequestBody VipUser user, BindingResult bindingResult, HttpServletRequest request) {
        return vipService.saveVipUser(user);
    }
}
