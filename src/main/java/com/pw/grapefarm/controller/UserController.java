package com.pw.grapefarm.controller;

import com.pw.grapefarm.annotation.ParamValid;
import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.dao.EmailCodeDao;
import com.pw.grapefarm.dao.UserDao;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.model.User;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{
    @Autowired
    UserDao userDao;

    @Autowired
    EmailCodeDao emailCodeDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @ParamValid
    @PostMapping
    public Response<Map> createUser(@Valid @RequestBody User user, BindingResult bindingResult){
        String email = user.getEmail();

        // 如果该邮件对应的用户已存在，则创建用户失败
        if(userDao.findByEmail(email) != null){
            return cResponse(StatusCode.user_email_exist.getCode(),StatusCode.user_email_exist.getRemark());
        }

        List<EmailCode> emailCodeList = emailCodeDao.findByEmailOrderBySendTimeDesc(user.getEmail());
        if(emailCodeList.size() == 0){
            return cResponse(StatusCode.user_code_not_exist.getCode(),StatusCode.user_code_not_exist.getRemark());
        }

        String lastestCode = emailCodeList.get(0).getCode();
        if(!lastestCode.toLowerCase().equals(user.getCode().toLowerCase())){
            return cResponse(StatusCode.user_code_incorrect.getCode(),StatusCode.user_code_incorrect.getRemark());
        }

        // TODO 将业务代码移至service层去
        String password  =  user.getPassword();
        user.setId(null);
        user.setUserName("");
        user.setFarmName("");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);

        return cResponse(COMMON_SUCCESS_CODE,"成功");
    }

}
