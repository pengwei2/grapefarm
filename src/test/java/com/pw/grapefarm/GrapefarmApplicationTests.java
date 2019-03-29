package com.pw.grapefarm;

import com.pw.grapefarm.daos.EmailCodeDao;
import com.pw.grapefarm.daos.UserDao;
import com.pw.grapefarm.models.EmailCode;
import com.pw.grapefarm.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrapefarmApplicationTests {

    @Autowired
    EmailCodeDao emailCodeDao;

    @Autowired
    UserDao userDao;

    @Test
    public void testUnit() {
        String email = "yodang2008@163.com";
        List<EmailCode> list = emailCodeDao.findByEmailOrderBySendTimeDesc(email);
        System.out.println(list);

        email = "xx@163.com";
        list = emailCodeDao.findByEmailOrderBySendTimeDesc(email);

        System.out.println(list);
    }

    @Test
    public void testUnit2() {
        String email = "yodang2008@163.com";

        User user = userDao.findByEmail(email);
        System.out.println(user);

        email = "xx@xx.com";
        user = userDao.findByEmail(email);
        System.out.println(user);
    }

}
