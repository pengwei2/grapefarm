package com.pw.grapefarm.service;

import com.pw.grapefarm.common.Response;
import com.pw.grapefarm.common.StatusCode;
import com.pw.grapefarm.dao.EmailCodeDao;
import com.pw.grapefarm.dao.UserDao;
import com.pw.grapefarm.model.EmailCode;
import com.pw.grapefarm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.pw.grapefarm.common.Response.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    EmailCodeDao emailCodeDao;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Response saveUser(User user) {
        String email = user.getEmail();

        // 如果该邮件对应的用户已存在，则创建用户失败
        if (userDao.findByEmail(email) != null) {
            return cResponse(StatusCode.user_email_exist.getCode(), StatusCode.user_email_exist.getRemark());
        }

        List<EmailCode> emailCodeList = emailCodeDao.findByEmailOrderBySendTimeDesc(user.getEmail());
        if (emailCodeList.size() == 0) {
            return cResponse(StatusCode.user_code_not_exist.getCode(), StatusCode.user_code_not_exist.getRemark());
        }

        String lastestCode = emailCodeList.get(0).getCode();
        if (!lastestCode.toLowerCase().equals(user.getCode().toLowerCase())) {
            return cResponse(StatusCode.user_code_incorrect.getCode(), StatusCode.user_code_incorrect.getRemark());
        }

        String password = user.getPassword();
        user.setId(null);
        user.setUserName("");
        user.setFarmName("");
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        userDao.save(user);

        return cResponse(COMMON_SUCCESS_CODE, "成功");
    }
}