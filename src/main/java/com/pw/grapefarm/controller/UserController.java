package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.User;
import com.pw.grapefarm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "用户controller",tags = {"用户操作接口"})
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @ApiOperation(value = "用户创建接口",
            notes = "statusCode=1代表返回成功" +
                    "\n statusCode=2代表发生常规错误" +
                    "\n statusCode=3代表该邮箱对应的用户已存在" +
                    "\n statusCode=4代表该邮箱对应的验证码不存在" +
                    "\n statusCode=5代表验证码不正确")
    @ParamValid
    @PostMapping
    public Response createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        return userService.saveUser(user);
    }


    @ApiOperation(value = "用户密码重置接口",
            notes = "statusCode=1代表返回成功" +
                    "\n statusCode=2代表发生常规错误" +
                    "\n statusCode=3该邮箱对应的用户不存在" +
                    "\n statusCode=4代表该邮箱对应的验证码不存在" +
                    "\n statusCode=5代表验证码不正确")
    @ParamValid
    @PostMapping(value = "/passwd")
    public Response updateUserPasswd(@Valid @RequestBody User user, BindingResult bindingResult){
        return userService.updateUserPasswd(user);
    }

}
