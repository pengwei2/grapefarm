package com.pw.grapefarm.controllers;

import com.pw.grapefarm.daos.UserDao;
import com.pw.grapefarm.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserDao userDao;

    @PostMapping
    public ResponseEntity<Object> createUser(){
        User user = new User();
        user.setEmail("aa");
        user.setPasswd("xx");
        user.setUserName("saa");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);
        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

}
