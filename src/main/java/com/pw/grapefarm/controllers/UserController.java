package com.pw.grapefarm.controllers;

import com.pw.grapefarm.annotations.ParamValid;
import com.pw.grapefarm.commons.Response;
import com.pw.grapefarm.daos.UserDao;
import com.pw.grapefarm.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    @Autowired
    UserDao userDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ParamValid
    @PostMapping
    public Response<String> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        String email = user.getEmail();

        // 如果该邮件对应的用户已存在，则创建用户失败
        if(userDao.findByEmail(email) != null){
            return new Response<>(StatusCode.fail.ordinal(),"失败","该邮箱对应的用户已存在！");
        }

        // TODO 将业务代码移至service层去
        String password  =  user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);

        return new Response<>(StatusCode.success.ordinal(),"成功","");
    }

}
