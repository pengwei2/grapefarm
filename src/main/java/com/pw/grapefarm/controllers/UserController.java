package com.pw.grapefarm.controllers;

import com.pw.grapefarm.commons.Response;
import com.pw.grapefarm.daos.UserDao;
import com.pw.grapefarm.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping
    public Response createUser(User user){
        return null;
    }

}
