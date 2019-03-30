package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.model.User;
import com.pw.grapefarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    UserService userService;

    @ParamValid
    @PostMapping
    public Response createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        return userService.saveUser(user);
    }

}
