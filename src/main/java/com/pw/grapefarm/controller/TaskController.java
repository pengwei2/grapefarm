package com.pw.grapefarm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "认证测试controller",tags = {"认证测试接口"})
@RestController
@RequestMapping("/task")
public class TaskController {

    @ApiOperation(value = "测试获取一个字符串",notes = "直接访问这个接口是没有权限的，应该先访问 /auth/login 接口获取token，然后携带这个token再访问需要授权的接口")
    @GetMapping
    public String listTasks(){
        return "任务列表";
    }

}
